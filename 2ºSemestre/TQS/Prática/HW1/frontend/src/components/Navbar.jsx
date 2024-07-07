import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <div className="navbar bg-base-100">
      <div className="flex-1">
        <Link to="/" className="btn btn-ghost text-4xl">
          BusWise
        </Link>
      </div>
      <div className="flex-none">
        <ul className="menu menu-horizontal px-1">
          <li>
            <Link to="/myreservations" className="text-lg font-semibold">My Reservations</Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default Navbar;
