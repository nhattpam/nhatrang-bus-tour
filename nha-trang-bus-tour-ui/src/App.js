import './App.css';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import { Route, Routes } from 'react-router-dom';
import SideBar from './components/SideBar';

function App() {
  return (
    // <>
    //   <Routes>
    //     <Route path='/' element={<Main/>}></Route>
    //     <Route path='/admin' element={<Navbar />}></Route>
    //   </Routes>
    // </>
    <>
     <Header />
     <Home />
     <SideBar />
     <Footer />
     

    </>

    
  );
}

export default App;
