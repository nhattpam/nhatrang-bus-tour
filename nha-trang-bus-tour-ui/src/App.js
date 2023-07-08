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
import AddBus from './components/buses/AddBus';
import AddDriver from './components/drivers/AddDriver';
import AddStation from './components/stations/AddStation';
import EditBus from './components/buses/EditBus';
import DeleteBus from './components/buses/DeleteBus';
import ListPayments from './components/payments/ListPayments';
import ListServices from './components/services/ListServices';
import AddService from './components/services/AddService';
import ListRoutes from './components/routes/ListRoutes';
import ListTicketTypes from './components/tickettypes/ListTicketTypes';
import ListPriceFrames from './components/priceframes/ListPriceFrames';
import ListPriceFrameTickets from './components/priceframetickets/ListPriceFrameTickets';

function App() {
  return (

    <>
      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/list_users' element={<ListUsers />}></Route>
        <Route path='/list_orders' element={<ListOrders />}></Route>
        <Route path='/list_buses' element={<ListBuses />}></Route>
        <Route path='/add_bus' element={<AddBus/>}></Route>
        <Route path='/edit_bus/:busId' element={<EditBus/>}></Route>
        <Route path='/delete_bus/:busId' element={<DeleteBus/>}></Route>
        <Route path='/list_tickets' element={<ListTickets />}></Route>
        <Route path='/list_drivers' element={<ListDrivers />}></Route>
        <Route path='/add_driver' element={<AddDriver />}></Route>
        <Route path='/list_trips' element={<ListTrips />}></Route>
        <Route path='/list_stations' element={<ListStation />}></Route>
        <Route path='/add_station' element={<AddStation />}></Route>
        <Route path='/list_payments' element={<ListPayments />}></Route>
        <Route path='/list_payments' element={<ListPayments />}></Route>
        <Route path='/list_services' element={<ListServices />}></Route>
        <Route path='/add_service' element={<AddService />}></Route>
        <Route path='/list_routes' element={<ListRoutes />}></Route>
        <Route path='/list_tickettypes' element={<ListTicketTypes />}></Route>
        <Route path='/list_priceframes' element={<ListPriceFrames />}></Route>
        <Route path='/list_priceframetickets' element={<ListPriceFrameTickets />}></Route>

      </Routes>

    </>


  );
};

export default App;
