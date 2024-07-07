import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import {
  FaChevronDown,
  FaLocationDot,
  FaMapLocationDot,
} from "react-icons/fa6";
import { FaSearch } from "react-icons/fa";

import axios from "axios";
import { API_URL } from "../api";
import { bus1 } from "../images";

const Hero = () => {
  const navigate = useNavigate();
  const [fromCities, setFromCities] = useState([]);
  const [toCities, setToCities] = useState([]);
  const [date, setDate] = useState("");
  const [fromCity, setFromCity] = useState("");
  const [toCity, setToCity] = useState("");

  const fetchFromCities = async () => {
    try {
      const res = await axios.get(`${API_URL}bustrips/get_from_cities`);

      if (res.status === 200) {
        setFromCities(res.data);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const fetchToCities = async () => {
    try {
      const res = await axios.get(`${API_URL}bustrips/get_to_cities`);

      if (res.status === 200) {
        setToCities(res.data);
      }
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchFromCities();
    fetchToCities();
  }, []);

  console.log(fromCity);
  console.log(toCity);

  return (
    <div
      className="hero min-h-screen"
      style={{ backgroundImage: `url(${bus1})` }}
    >
      <div className="hero-overlay bg-opacity-70"></div>
      <div className="hero-content text-center text-neutral-content">
        <div className="max-w-lg justify-center">
          <h1 className="mb-5 text-5xl font-bold text-nowrap">
            Welcome to Bus Wize
          </h1>
          <p className="mb-5">
            Welcome to BusWize, your go-to destination for hassle-free bus trip
            reservations. Browse, book, and embark on your journey with ease,
            all at your fingertips.
          </p>

          <div className="card w-[43rem] h-full bg-base-100 shadow-xl">
            <div className="card-body">
              <h2 className="card-title mb-3">
                Search for your ideal trip here
              </h2>
              <div className="flex flex-col">
                <div className="flex flex-row justify-between">
                <label className="form-control w-full">
                    <div className="label">
                      <span className="label-text">Departure City</span>
                    </div>
                    <select
                      className="select select-primary w-full max-w-xs"
                      value={toCity ? toCity : ""}
                      onChange={(e) => setToCity(e.target.value)}
                    >
                      <option disabled>Choose your departure city</option>
                      {fromCities.length > 0 &&
                        fromCities.map((city, index) => (
                          <option
                            key={index}
                            value={city}
                            onChange={(e) => setToCity(e.target.value)}
                          >
                            {city}
                          </option>
                        ))
                      }
                    </select>
                  </label>

                  <div className="divider divider-horizontal h-[11rem]"></div>
                  <label className="form-control w-full">
                    <div className="label">
                      <span className="label-text">Destination City</span>
                    </div>
                    <select
                      className="select select-primary w-full max-w-xs"
                      value={toCity ? toCity : ""}
                      onChange={(e) => setToCity(e.target.value)}
                    >
                      <option disabled>Choose your destination city</option>
                      {toCities.length > 0 &&
                        toCities.map((city, index) => (
                          <option
                            key={index}
                            value={city}
                            onChange={(e) => setToCity(e.target.value)}
                          >
                            {city}
                          </option>
                        ))}
                    </select>
                  </label>
                </div>
                <input
                  type="date"
                  placeholder="Choose a date"
                  className="input input-bordered input-primary w-full mt-5"
                  onChange={(e) => setDate(e.target.value)}
                />
                <div className="flex flex-row space-x-4 justify-center pt-5">
                  {(fromCity || toCity || date) && (
                    <button
                      className="btn btn-outline btn-accent"
                      onClick={() =>
                        navigate(
                          `/bustrips?${fromCity && `&from=${fromCity}`}${
                            toCity && `&to=${toCity}`
                          }${date && `&date=${date}`}`
                        )
                      }
                    >
                      Search <FaSearch />
                    </button>
                  )}
                  <button
                    className="btn btn-outline btn-secondary"
                    onClick={() => navigate(`/bustrips`)}
                  >
                    See all trips <FaMapLocationDot />
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Hero;
