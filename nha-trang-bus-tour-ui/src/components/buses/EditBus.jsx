import React, { useEffect, useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import busService from '../../services/bus.service';
import { useNavigate, useParams } from 'react-router-dom';

const EditBus = () => {
  const [bus, setBus] = useState({
    busId: '',
    busNumber: '',
    seat: ''
  });

  const [errors, setErrors] = useState({});
  const [msg, setMsg] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    const value = e.target.value;
    setBus({ ...bus, [e.target.name]: value });
  };

  const { busId } = useParams();

  useEffect(() => {
    if (busId) {
      busService
        .getBusById(busId)
        .then((res) => {
          setBus(res.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [busId]);

  const validateForm = () => {
    let isValid = true;
    const errors = {};
  
    if (bus.busNumber.trim() === '') {
      errors.busNumber = 'Bus Number is required';
      isValid = false;
    }
  
    if (!bus.seat || +bus.seat <= 0) {
      errors.seat = 'Seat should be a positive number';
      isValid = false;
    }
  
    setErrors(errors);
    return isValid;
  };
  

  const submitBus = (e) => {
    e.preventDefault();

    if (validateForm()) {
      busService
        .updateBus(bus.busId, bus) 
        .then((res) => {
          navigate("/list_buses/");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  return (
    <>
      <div id="wrapper">
        <SideBar />
        <div id="content-wrapper" className="d-flex flex-column">
          <div id="content">
            <Header />
            <div className="container-fluid">
              <h1 className="h3 mb-2 text-gray-800">Update Bus</h1>

              <form onSubmit={submitBus}>
                <div>
                  <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Bus Number</label>
                    <input
                      type="text"
                      name="busNumber"
                      value={bus.busNumber}
                      onChange={handleChange}
                      className={`form-control ${errors.busNumber ? 'is-invalid' : ''}`}
                      id="exampleInputEmail1"
                      aria-describedby="emailHelp"
                    />
                    {errors.busNumber && (
                      <div className="invalid-feedback">{errors.busNumber}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <label htmlFor="exampleInputPassword1">Seat</label>
                    <input
                      type="number"
                      name="seat"
                      value={bus.seat}
                      onChange={handleChange}
                      className={`form-control ${errors.seat ? 'is-invalid' : ''}`}
                      id="exampleInputPassword1"
                    />
                    {errors.seat && (
                      <div className="invalid-feedback">{errors.seat}</div>
                    )}
                  </div>

                  <button type="submit" className="btn btn-primary">Save</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default EditBus;
