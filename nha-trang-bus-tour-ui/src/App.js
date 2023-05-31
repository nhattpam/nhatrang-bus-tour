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
      <div id="content">
                {/* Page Wrapper */}

                <div id="wrapper">
                    <SideBar />
                    {/* Content Wrapper */}

                    <div id="content-wrapper" class="d-flex flex-column">
                        {/* Main Content */}

                        <div id="content">
                            <Header />
                            
                        </div>
                    </div>
                </div>

            </div>

      <Routes>
        <Route path='/' element={<Home />}></Route>
        <Route path='/list_users' element={<ListUsers />}></Route>
        <Route path='/list_orders' element={<ListOrders />}></Route>
        <Route path='/list_buses' element={<ListUsers />}></Route>

      </Routes>

    </>


  );
}

export default App;
