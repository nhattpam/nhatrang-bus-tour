import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import { Route, Routes } from 'react-router-dom';
import SideBar from './components/SideBar';
import ListUsers from './components/users/ListUsers';
import ListOrders from './components/orders/ListOrders';
import ListBuses from './components/buses/ListBuses';
import ListTickets from './components/tickets/ListTickets';
import ListDrivers from './components/drivers/ListDrivers';
import ListTrips from './components/trips/ListTrips';
import ListStation from './components/stations/ListStation';

function App() {
  return (

    <>
      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/list_users' element={<ListUsers />}></Route>
        <Route path='/list_orders' element={<ListOrders />}></Route>
        <Route path='/list_buses' element={<ListBuses />}></Route>
        <Route path='/list_tickets' element={<ListTickets />}></Route>
        <Route path='/list_drivers' element={<ListDrivers />}></Route>
        <Route path='/list_trips' element={<ListTrips />}></Route>
        <Route path='/list_stations' element={<ListStation />}></Route>

      </Routes>

    </>


  );
};

export default App;
