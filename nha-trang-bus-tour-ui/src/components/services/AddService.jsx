import React, { useState } from 'react'
import SideBar from '../SideBar'
import Header from '../Header'
import servService from '../../services/serv.service';

const AddService = () => {

  const [service, setService] = useState({
    serviceNumber: "",
    serviceName: ""
  });

  const [errors, setErrors] = useState({});
  const [msg, setMsg] = useState("");

  const handleChange = (e) => {
    const value = e.target.value;
    setService({ ...service, [e.target.name]: value });
  }

  const validateForm = () => {
    let isValid = true;
    const errors = {};

    if (service.serviceName.trim() === '') {
      errors.serviceName = 'Name is required';
      isValid = false;
    }

    if (service.serviceNumber.trim() === '') {
      errors.serviceNumber = 'Number is required';
      isValid = false;
    } else if (isNaN(service.serviceNumber) || +service.serviceNumber <= 0) {
      errors.serviceNumber = 'Number should be a positive number';
      isValid = false;
    }

    setErrors(errors);
    return isValid;
  };

  const submitService = (e) => {
    e.preventDefault();

    if (validateForm()) {
      servService
        .saveService(service)
        .then((res) => {
          console.log(service);
          setMsg('Service Added Successfully');
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

              <h1 class="h3 mb-2 text-gray-800">Add Service</h1>

              {
                msg && <p className='text-center text-success'>
                  {msg}
                </p>
              }

              <form onSubmit={(e) => submitService(e)}>
                <div>
                  <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Number</label>
                    <input type="number" name='serviceNumber' value={service.serviceNumber} onChange={(e) => handleChange(e)}
                      className={`form-control ${errors.serviceNumber ? 'is-invalid' : ''
                        }`} id="exampleInputEmail1" aria-describedby="emailHelp" x />
                    {errors.serviceNumber && (
                      <div className="invalid-feedback">{errors.serviceNumber}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <label htmlFor="text">Name</label>
                    <input type="text" name='serviceName' value={service.serviceName} onChange={(e) => handleChange(e)}
                      className={`form-control ${errors.serviceName ? 'is-invalid' : ''}`} id="exampleInputPassword1" />
                    {errors.serviceName && (
                      <div className="invalid-feedback">{errors.serviceName}</div>
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

export default AddService