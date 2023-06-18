import React, { useState } from 'react';
import SideBar from '../SideBar';
import Header from '../Header';
import stationService from '../../services/station.service';

const AddStation = () => {
  const [station, setStation] = useState({
    stationName: '',
    stationLocation: '',
  });

  const [errors, setErrors] = useState({});
  const [msg, setMsg] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStation((prevStation) => ({
      ...prevStation,
      [name]: value,
    }));
  };

  const validateForm = () => {
    let isValid = true;
    const errors = {};

    if (station.stationName.trim() === '') {
      errors.stationName = 'Station Name is required';
      isValid = false;
    } else if (station.stationName.length > 100) {
      errors.stationName = 'Station Name should not exceed 100 characters';
      isValid = false;
    }

    if (station.stationLocation.trim() === '') {
      errors.stationLocation = 'Location is required';
      isValid = false;
    } else if (station.stationLocation.length > 100) {
      errors.stationLocation = 'Location should not exceed 100 characters';
      isValid = false;
    }

    setErrors(errors);
    return isValid;
  };

  const submitStation = (e) => {
    e.preventDefault();

    if (validateForm()) {
      stationService
        .saveStation(station)
        .then((res) => {
          console.log(station);
          setMsg('Station Added Successfully');
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
              <h1 className="h3 mb-2 text-gray-800">Add Station</h1>
              {msg && <p className="text-center text-success">{msg}</p>}
              <form onSubmit={submitStation}>
                <div>
                  <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Station Name</label>
                    <input
                      type="text"
                      name="stationName"
                      value={station.stationName}
                      onChange={handleChange}
                      className={`form-control ${
                        errors.stationName ? 'is-invalid' : ''
                      }`}
                      id="exampleInputEmail1"
                      aria-describedby="emailHelp"
                    />
                    {errors.stationName && (
                      <div className="invalid-feedback">{errors.stationName}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <label htmlFor="exampleInputPassword1">Location</label>
                    <input
                      type="text"
                      name="stationLocation"
                      value={station.stationLocation}
                      onChange={handleChange}
                      className={`form-control ${
                        errors.stationLocation ? 'is-invalid' : ''
                      }`}
                      id="exampleInputPassword1"
                    />
                    {errors.stationLocation && (
                      <div className="invalid-feedback">{errors.stationLocation}</div>
                    )}
                  </div>
                  <button type="submit" className="btn btn-primary">
                    Save
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default AddStation;
