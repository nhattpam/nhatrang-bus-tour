import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import tripService from '../../services/trip.service';
const ListTrips = () => {

    const [tripList, setTripList] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');


    useEffect(() => {
        tripService.getAllTrips().then((res) => {
            console.log(res.data);

            setTripList(res.data);
        }).catch((error) => {
            console.log(error);
            console.log('lol');
        });
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredTrips = tripList.filter((trip) => {
        return (
            trip.tripID.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            trip.departureTime.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            trip.arrivalTime.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            trip.bus.busNumber.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            trip.driver.driverName.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            trip.priceFrame.priceFrameName.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            trip.route.routeName.toString().toLowerCase().includes(searchTerm.toLowerCase())
        );
    });


    return (
        <>
            {/* Page Wrapper */}

            <div id="wrapper">
                <SideBar />
                {/* Content Wrapper */}

                <div id="content-wrapper" class="d-flex flex-column">
                    {/* Main Content */}

                    <div id="content">
                        <Header />
                        <div class="container-fluid">

                            {/* Page Heading */}

                            <h1 class="h3 mb-2 text-gray-800">List Trips</h1>
                            {/* Search Input */}
                            <div className="mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Search Trips"
                                    value={searchTerm}
                                    onChange={handleSearch}
                                />
                            </div>

                            {/* DataTales Example */}

                            <div class="card shadow mb-4">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Trip Id</th>
                                                    <th>Departure Time</th>
                                                    <th>Arrival Time</th>
                                                    <th>Bus</th>
                                                    <th>Driver</th>
                                                    <th>Price Frame</th>
                                                    <th>Route</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                {

                                                    filteredTrips.map((trip) => (
                                                        <tr key={trip.tripID}>
                                                            <td>{trip.tripID}</td>
                                                            <td>{trip.departureTime}</td>
                                                            <td>{trip.arrivalTime}</td>
                                                            <td>{trip.bus.busNumber}</td>
                                                            <td>{trip.driver.driverName}</td>
                                                            <td>{trip.priceFrame.priceFrameName}</td>
                                                            <td>{trip.route.routeName}</td>
                                                        </tr>
                                                    ))

                                                }

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                        </div>
                        {/* /.container-fluid */}

                    </div>
                </div>
            </div>

        </>
    )
}

export default ListTrips