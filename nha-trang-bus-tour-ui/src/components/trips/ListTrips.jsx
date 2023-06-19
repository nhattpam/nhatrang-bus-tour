import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import tripService from '../../services/trip.service';
const ListTrips = () => {

    const [tripList, setTripList] = useState([]);

    useEffect(() => {
        tripService.getAllTrips().then((res) => {
            console.log(res.data);

            setTripList(res.data);
        }).catch((error) => {
            console.log(error);
            console.log('lol');
        });
    }, []);


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

                                                    tripList.map((e) => (
                                                        <tr>
                                                            <td key={e.tripID}>{e.tripID}</td>
                                                            <td key={e.departureTime}>{e.departureTime}</td>
                                                            <td key={e.arrivalTime}>{e.arrivalTime}</td>
                                                            <td key={e.bus.busNumber}>{e.bus.busNumber}</td>
                                                            <td key={e.driver.driverName}>{e.driver.driverName}</td>
                                                            <td key={e.priceFrame.priceFrameName}>{e.priceFrame.priceFrameName}</td>
                                                            <td key={e.route.routeName}>{e.route.routeName}</td>
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