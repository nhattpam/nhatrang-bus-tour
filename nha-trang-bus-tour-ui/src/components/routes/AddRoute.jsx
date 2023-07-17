import React, { useState } from 'react'
import SideBar from '../SideBar'
import Header from '../Header'
import busService from '../../services/bus.service';
const AddRoute = () => {


    const [bus, setBus] = useState({
        busNumber: "",
        seat: ""
    });

    const [errors, setErrors] = useState({});
    const [msg, setMsg] = useState("");

    const handleChange = (e) => {
        const value = e.target.value;
        setBus({ ...bus, [e.target.name]: value });
    }

    const validateForm = () => {
        let isValid = true;
        const errors = {};

        if (bus.busNumber.trim() === '') {
            errors.busNumber = 'Bus Number is required';
            isValid = false;
        }

        if (bus.seat.trim() === '') {
            errors.seat = 'Seat is required';
            isValid = false;
        } else if (isNaN(bus.seat) || +bus.seat <= 0) {
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
                .saveBus(bus)
                .then((res) => {
                    console.log(bus);
                    setMsg('Bus Added Successfully');
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }

    return (
        <>
            <div id="wrapper">
                <SideBar />
                {/* Content Wrapper */}

                <div id="content-wrapper" class="d-flex flex-column">
                    {/* Main Content */}

                    <div id="content">
                        <Header />
                        <div class="container-fluid">

                            {/* Page Heading */}

                            <h1 class="h3 mb-2 text-gray-800">Add Route</h1>

                            {
                                msg && <p className='text-center text-success'>
                                    {msg}
                                </p>
                            }

                            <form onSubmit={(e) => submitBus(e)}>
                                <div>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputEmail1">Bus Number</label>
                                        <input type="text" name='busNumber' value={bus.busNumber} onChange={(e) => handleChange(e)}
                                            className={`form-control ${errors.busNumber ? 'is-invalid' : ''
                                                }`} id="exampleInputEmail1" aria-describedby="emailHelp" x />
                                        {errors.busNumber && (
                                            <div className="invalid-feedback">{errors.busNumber}</div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputPassword1">Seat</label>
                                        <input type="number" name='seat' value={bus.seat} onChange={(e) => handleChange(e)}
                                            className={`form-control ${errors.seat ? 'is-invalid' : ''}`} id="exampleInputPassword1" />
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

    )
}

export default AddRoute