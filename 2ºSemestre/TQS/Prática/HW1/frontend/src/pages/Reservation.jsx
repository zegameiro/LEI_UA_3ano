import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import Navbar from "../components/Navbar";

import axios from "axios";
import { API_URL } from "../api";
import {  FaLocationDot } from "react-icons/fa6";

const Reservation = () => {
  const [busTrip, setBusTrip] = useState({});
  const [busInfo, setBusInfo] = useState({});
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");

  const busTripId = new URLSearchParams(window.location.search).get("id");

  const fetchBusTrip = async () => {
    try {
      const res = await axios.get(
        `${API_URL}bustrips/get_bus_trip?id=${busTripId}`
      );

      if (res.status === 200) {
        console.log("Retrieved bus trip -> ", res.data);
        setBusTrip(res.data);

        if (res.data.busId) {
          const res2 = await axios.get(
            `${API_URL}bus/get?id=${res.data.busId}`
          );

          if (res2.status === 200) {
            console.log("Retrieved bus info -> ", res2.data);
            setBusInfo(res2.data);
          }
        }
      }
    } catch (error) {
      console.error(error);
    }
  };


  const buyReservation = async () => {

    let data = {
      idBusTrip: busTrip.id,
      firstName: firstName,
      lastName: lastName,
      email: email,
      phone: phoneNumber,
      seat: selectedSeat.id,
    }

    try {
      // Send a request to the server to buy the reservation through the request body with hearders
      const res = await axios.post(`${API_URL}reservations/buy`, JSON.stringify(data), {
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (res.status === 200) {
        console.log("Reservation bought -> ", res.data);
      }
    } catch (error) {
      console.error(error);
    }
  };


  useEffect(() => {
    fetchBusTrip();
  }, []);

  return (
    <div>
      <Navbar />
      <div className="bg-white h-full px-[5rem] py-[2rem]">
        <h1 className="text-5xl font-bold">Reservation</h1>
        <div className="divider bg-slate-600 h-0.5 rounded"></div>
        {busTrip && (
          <div className="collapse bg-slate-400 text-black">
            <input type="radio" name="my-accordion-1" defaultChecked />
            <div className="collapse-title text-xl font-medium flex flex-row">
              BusTrip from {busTrip.fromCity} <FaLocationDot />
                 to {busTrip.toCity}  <FaLocationDot />   
            </div>

            <div className="flex flex-col space-y-2 collapse-content">
              <div className="flex flex-row gap-3">
                <span className="font-semibold">Date:</span>
                <span>{busTrip.date}</span>
              </div>

              <div className="flex flex-row gap-3">
                <span className="font-semibold">Time:</span>
                <span>{busTrip.time}</span>
              </div>

              <div className="flex flex-row gap-3">
                <span className="font-semibold">Bus:</span>
                <span>{busInfo.name}</span>
              </div>

              <div className="flex flex-row gap-3">
                <span className="font-semibold">Price:</span>
                <span>{busTrip.price}</span>
              </div>

              <div className="flex flex-row gap-3">
                <span className="font-semibold">Available Seats:</span>
                <span>
                  {busTrip.seats?.filter((seat) => !seat.isTaken).length}
                </span>
              </div>
            </div>
          </div>
        )}
        <form action="">
          <div className="flex flex-row pt-5 justify-between">
            <div className="flex flex-col space-y-4">
              <label className="form-control w-full">
                <div className="label">
                  <span className="label-text">First Name:</span>
                </div>
                <input
                  type="text"
                  id="first_name"
                  placeholder="Ex: John"
                  className="input input-bordered bg-slate-200 w-[30rem] text-black"
                  onChange={(e) => setFirstName(e.target.value)}
                />
              </label>
              <label className="form-control w-full">
                <div className="label">
                  <span className="label-text">Last Name:</span>
                </div>
                <input
                  type="text"
                  id="last_name"
                  placeholder="ex: Smith"
                  className="input input-bordered bg-slate-200 w-[30rem] text-black"
                  onChange={(e) => setLastName(e.target.value)}
                />
              </label>
              <label className="form-control w-full">
                <div className="label">
                  <span className="label-text">Email:</span>
                </div>
                <input
                  type="email"
                  id="email"
                  placeholder="ex: name@domain.com"
                  className="input input-bordered bg-slate-200 w-[30rem] text-black"
                  onChange={(e) => setEmail(e.target.value)}
                />
              </label>
              <label className="form-control w-full">
                <div className="label">
                  <span className="label-text">Phone Number</span>
                </div>
                <input
                  type="number"
                  id="phone_number"
                  placeholder="ex:123456789"
                  className="input input-bordered bg-slate-200 w-[30rem] text-black"
                  onChange={(e) => setPhoneNumber(e.target.value)}
                />
              </label>
              {firstName && lastName && phoneNumber && email && selectedSeat && (
                <Link to="/myreservations">
                  <button
                    className="btn btn-outline btn-success"
                    onClick={() => buyReservation()}
                  >
                    Buy reservation
                  </button>
                </Link>
              )}
            </div>

            <div className="grid grid-cols-5 gap-4">
              {busTrip.seats?.map(
                (seat, index) => (
                  <button
                    disabled={seat.isTaken}
                    id={index}
                    className={`btn ${
                      seat.seatType === "Economic"
                        ? "btn-secondary"
                        : "btn-primary"
                    } ${selectedSeat&& selectedSeat.id === seat.id ? "": "btn-outline"}`}
                    key={index}
                    onClick={(e) => e.preventDefault() & setSelectedSeat(seat)}
                  >
                    Seat {index + 1} <br />
                    {seat.seatType}
                  </button>
                ),
                []
              )}
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Reservation;
