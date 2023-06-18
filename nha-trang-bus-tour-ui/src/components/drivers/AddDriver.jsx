import React, { useState } from 'react'
import SideBar from '../SideBar'
import Header from '../Header'
import driverService from '../../services/driver.service';

const AddDriver = () => {


    const [driver, setDriver] = useState({
        driverPhone: "",
        driverName: ""
    });

    const [errors, setErrors] = useState({});
    const [msg, setMsg] = useState("");

    const handleChange = (e) => {
        const value = e.target.value;
        setDriver({ ...driver, [e.target.name]: value });
    }

    const validateForm = () => {
        let isValid = true;
        const errors = {};

        if (driver.driverPhone.trim() === '') {
            errors.driverPhone = 'Phone Number is required';
            isValid = false;
        } else if (!/^\d{10}$/.test(driver.driverPhone)) {
            errors.driverPhone = 'Phone Number should be a 10-digit number';
            isValid = false;
        }

        if (driver.driverName.trim() === '') {
            errors.driverName = 'Name is required';
            isValid = false;
        }

        setErrors(errors);
        return isValid;
    };

    const submitDriver = (e) => {
        e.preventDefault();

        if (validateForm()) {
            driverService
                .saveDriver(driver)
                .then((res) => {
                    console.log(driver);
                    setMsg('Driver Added Successfully');
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

                            <h1 class="h3 mb-2 text-gray-800">Add Driver</h1>

                            {
                                msg && <p className='text-center text-success'>
                                    {msg}
                                </p>
                            }

                            <form onSubmit={(e) => submitDriver(e)}>
                                <div>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputEmail1">Phone Number</label>
                                        <input type="text" name='driverPhone' value={driver.driverPhone} onChange={(e) => handleChange(e)}
                                            className={`form-control ${errors.driverPhone ? 'is-invalid' : ''
                                                }`} id="exampleInputEmail1" aria-describedby="emailHelp" x />
                                        {errors.driverPhone && (
                                            <div className="invalid-feedback">{errors.driverPhone}</div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputPassword1">Name</label>
                                        <input type="text" name='driverName' value={driver.driverName} onChange={(e) => handleChange(e)}
                                            className={`form-control ${errors.driverName ? 'is-invalid' : ''
                                                }`} id="exampleInputPassword1" />
                                        {errors.driverName && (
                                            <div className="invalid-feedback">{errors.driverName}</div>
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

export default AddDriver