import React, { useState, useEffect } from 'react'
import SideBar from '../SideBar'
import Header from '../Header'
import tripService from '../../services/trip.service';
import busService from '../../services/bus.service';
import driverService from '../../services/driver.service';
import routeService from '../../services/route.service';
import priceFrameService from '../../services/priceframe.service';


const AddTrip = () => {

    const [busOptions, setBusOptions] = useState([]);
    const [driverOptions, setDriverOptions] = useState([]);
    const [routeOptions, setRouteOptions] = useState([]);
    const [priceFrameOptions, setPriceFrameOptions] = useState([]);
    useEffect(() => {
        // Fetch the buses when the component mounts
        busService.getAllBuses()
            .then((response) => {
                setBusOptions(response.data);
            })
            .catch((error) => {
                console.error('Error fetching buses:', error);
            });

        driverService.getAllDrivers()
            .then((response) => {
                setDriverOptions(response.data);
            })
            .catch((error) => {
                console.error('Error fetching drivers:', error);
            });

        routeService.getAllRoutes()
            .then((response) => {
                setRouteOptions(response.data);
            })
            .catch((error) => {
                console.error('Error fetching routes:', error);
            });
        priceFrameService.getAllPriceFrames()
            .then((response) => {
                setPriceFrameOptions(response.data);
            })
            .catch((error) => {
                console.error('Error fetching priceFrames:', error);
            });


    }, []);
    const [trip, setTrip] = useState({
        departureTime: "",
        arrivalTime: "",
        busId: "",
        driverId: "",
        priceFrameId: "",
        routeId: ""
    });

    const [errors, setErrors] = useState({});
    const [msg, setMsg] = useState("");

    const handleChange = (e) => {
        const value = e.target.value;
        setTrip({ ...trip, [e.target.name]: value });
    }


    const submitBus = (e) => {
        console.log(trip);
        e.preventDefault();
        tripService
            .saveTrip(trip)
            .then((res) => {

                setMsg('Trip Added Successfully');
            })
            .catch((error) => {
                setMsg('Trip Added Failed');
                console.log(error);
            });
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

                            <h1 class="h3 mb-2 text-gray-800">Add Trip</h1>

                            {
                                msg && <p className='text-center text-success'>
                                    {msg}
                                </p>
                            }

                            <form onSubmit={(e) => submitBus(e)}>
                                <div>
                                    <div className="form-group">

                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputEmail1">Departure Time </label>
                                        <input type="date" name='departureTime' value={trip.departureTime} onChange={(e) => handleChange(e)}
                                            className={`form-control`} id="exampleInputEmail1" aria-describedby="emailHelp" x />
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputEmail1">Arrival Time </label>
                                        <input type="date" name='arrivalTime' value={trip.arrivalTime} onChange={(e) => handleChange(e)}
                                            className={`form-control`} id="exampleInputEmail1" aria-describedby="emailHelp" x />
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="busSelect">Bus</label>
                                        <select
                                            name="busId" // Update name to 'busId' to store the selected bus ID
                                            value={trip.busId} // Use 'trip.busId' to set the selected bus ID
                                            onChange={handleChange}
                                            className="form-control"
                                            id="busSelect"
                                        >
                                            <option value="">Select a bus</option>
                                            {busOptions.map((bus) => (
                                                <option key={bus.busId} value={bus.busId}>
                                                    {bus.busNumber}
                                                </option>
                                            ))}
                                        </select>
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="driverSelect">Driver</label>
                                        <select
                                            name="driverId" // Update name to 'driverId' to store the selected driver ID
                                            value={trip.driverId} // Use 'trip.driverId' to set the selected driver ID
                                            onChange={handleChange}
                                            className="form-control"
                                            id="driverSelect"
                                        >
                                            <option value="">Select a driver</option>
                                            {driverOptions.map((driver) => (
                                                <option key={driver.driverId} value={driver.driverId}>
                                                    {driver.driverPhone}
                                                </option>
                                            ))}
                                        </select>
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="routeSelect">Route</label>
                                        <select
                                            name="routeId" // Update name to 'driverId' to store the selected driver ID
                                            value={trip.routeId} // Use 'trip.driverId' to set the selected driver ID
                                            onChange={handleChange}
                                            className="form-control"
                                            id="routeSelect"
                                        >
                                            <option value="">Select a Route</option>
                                            {routeOptions.map((route) => (
                                                <option key={route.routeId} value={route.routeId}>
                                                    {route.routeName}
                                                </option>
                                            ))}
                                        </select>
                                    </div>

                                    <div className="form-group">
                                        <label htmlFor="priceFrameSelect">Price Frame</label>
                                        <select
                                            name="priceFrameId" // Update name to 'driverId' to store the selected driver ID
                                            value={trip.priceFrameId} // Use 'trip.driverId' to set the selected driver ID
                                            onChange={handleChange}
                                            className="form-control"
                                            id="priceFrameSelect"
                                        >
                                            <option value="">Select a Price Frame</option>
                                            {priceFrameOptions.map((priceFrame) => (
                                                <option key={priceFrame.priceFrameId} value={priceFrame.priceFrameId}>
                                                    {priceFrame.priceFrameName}
                                                </option>
                                            ))}
                                        </select>
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

export default AddTrip