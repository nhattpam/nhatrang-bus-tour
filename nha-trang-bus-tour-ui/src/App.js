import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import Main from './components/Main';
import Navbar from './components/Navbar';
function App() {
  return (
    <>
      <Routes>
        <Route path='/' element={<Main/>}></Route>
        <Route path='/admin' element={<Navbar />}></Route>
      </Routes>
    </>
    
  );
}

export default App;
