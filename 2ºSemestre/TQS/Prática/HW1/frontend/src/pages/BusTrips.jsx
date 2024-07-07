import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import Navbar from "../components/Navbar";
import { API_URL } from "../api";

import {
  FaChevronDown,
  FaLocationDot,
} from "react-icons/fa6";
import { FaMoneyBillWave, FaCalendar, FaClock, FaBus } from "react-icons/fa";
import { MdAirlineSeatReclineNormal } from "react-icons/md";


import axios from "axios";

const BusTrips = () => {

  const navigate = useNavigate();
  const fromCity = new URLSearchParams(window.location.search).get("from");
  const toCity = new URLSearchParams(window.location.search).get("to");
  const date = new URLSearchParams(window.location.search).get("date");
  const [busTrips, setBusTrips] = useState([]);
  const [searchMessage, setSearchMessage] = useState("");
  const [busInfo, setBusInfo] = useState({});
  const [currency, setCurrency] = useState("EUR");

  const fetchBusTrips = async () => {
    try {
      const res = await axios.get(`${API_URL}bustrips/get${currency !== null ? `?currency=${currency}` : ""}${fromCity !== null ? `&fromCity=${fromCity}` : ""}${toCity !== null ? `&toCity=${toCity}` : ""}${date !== null ? `&date=${date}` : ""}`);

      if (res.status === 200) {
        if (res.data.length === 0) {
          setSearchMessage("No bus trips found.");

          const res2 = await axios.get(`${API_URL}bustrips/get?`);

          if (res2.status === 200) {
            console.log("Retrieved all bus trips -> ", res2.data);
            setBusTrips(res2.data);
          }
        } else {
          console.log("Retrieved bus trips -> ", res.data);
          setBusTrips(res.data);
        }
      }
    } catch (error) {
      console.error(error);
    }
  };

  const getBusInfo = async () => {
    try {
      const res = await axios.get(`${API_URL}bus/getAll`);

      if (res.status === 200) {
        console.log("Retrieved all buses -> ", res.data);
        const buses = res.data;
        const b = {};
        for (const bus of buses) {
          b[bus.id] = bus.name;
        }
        console.log("Bus info -> ", b);
        setBusInfo(b);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchBusTrips();
    getBusInfo();
  }, [currency]);

  return (
    <div>
      <Navbar />
      <div className="bg-white h-screen px-[5rem] py-[2rem]">
        <div className="flex flex-row justify-between">
          <h1 className="text-5xl font-bold">Bus Trips</h1>
          <div className="dropdown">
            <div tabIndex={0} role="button" className="btn btn-outline btn-primary text-nowrap mt-5 w-[10rem]">Currency: {currency} <FaChevronDown /> </div>
            <ul tabIndex={0} className="dropdown-content z-[1] menu p-2 shadow bg-base-200 rounded-box w-full">
              <li><a onClick={() => setCurrency(currency === "EUR" ? "USD" : "EUR") & navigate(`/bustrips${currency !== null && `?currency=${currency === "EUR" ? "USD" : "EUR"}`}${fromCity !== null ? `&from=${fromCity}` : ""}${toCity !== null ? `&to=${toCity}` : ""}${date !== null ? `&date=${date}` : ""}`)}>{currency === "EUR" ? "USD" : "EUR"}</a></li>
            </ul>
          </div>
        </div>
        <div className="divider bg-slate-600 h-0.5 rounded"></div>
        {searchMessage && (
          <div className="text-black text-xl">{searchMessage}</div>
        )}
        <div className="overflow-x-auto">
          <table className="table text-black">
            {/* head */}
            <thead>
              <tr className="text-slate-600 text-lg">
                <th></th>
                <th className="items-center gap-1">Departure City <FaLocationDot /></th>
                <th className="items-center gap-1">Destination City  <FaLocationDot /></th>
                <th className="items-center gap-1">Date <FaCalendar /></th>
                <th className="items-center gap-1">Time <FaClock /></th>
                <th className="items-center gap-1">Bus <FaBus /></th>
                <th className="items-center gap-1">Price <FaMoneyBillWave /></th>
                <th className="items-center gap-1">Available seats <MdAirlineSeatReclineNormal /></th>
              </tr>
            </thead>
            <tbody>
              {busTrips.length > 0 &&
                busTrips.map((busTrip, index) => (
                  <tr key={index}>
                    <th>{index + 1}</th>
                    <td id="from_city">{busTrip.fromCity}</td>
                    <td id="to_city">{busTrip.toCity}</td>
                    <td id="date">{busTrip.date}</td>
                    <td id="time">{busTrip.time}</td>
                    <td id="bus_name">{busInfo[busTrip.busId]}</td>
                    <td id="price">{busTrip.price}</td>
                    <td id="seats_available">
                      {busTrip.seats.filter((seat) => !seat.isTaken).length}
                    </td>
                    <td>
                      <button className="btn btn-sm btn-outline btn-primary" onClick={() => navigate(`/reservation?id=${busTrip.id}`)}>
                        Choose Trip
                      </button>
                    </td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default BusTrips;
