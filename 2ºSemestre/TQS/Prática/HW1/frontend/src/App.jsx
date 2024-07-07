import { BrowserRouter, Route, Routes } from 'react-router-dom'

import HomePage from './pages/HomePage';
import BusTrips from './pages/BusTrips';
import Reservation from './pages/Reservation';
import MyReservations from './pages/MyReservations';

const App = () => {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/bustrips" element={<BusTrips />} />
          <Route path="/reservation" element={<Reservation />} />
          <Route path="/myreservations" element={<MyReservations />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App
