import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import { Route, Routes } from 'react-router-dom';
import SideBar from './components/SideBar';
import ListUsers from './components/users/ListUsers';
import ListOrders from './components/orders/ListOrders';
import ListBuses from './components/buses/ListBuses';

function App() {
  return (

    <>
      <Header />
      <SideBar />
      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/users/list_users' element={<ListUsers/>}></Route>
        <Route path='/orders/list_orders' element={<ListOrders/>}></Route>
        <Route path='/buses/list_buses' element={<ListUsers/>}></Route>

      </Routes>

    </>


  );
}

export default App;
