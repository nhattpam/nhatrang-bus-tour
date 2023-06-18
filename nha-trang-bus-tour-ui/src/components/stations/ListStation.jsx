import React, { useEffect, useState } from 'react'
import Header from '../Header'
import SideBar from '../SideBar'
import stationService from '../../services/station.service';

const ListStation = () => {


    const [stationList, setStationList] = useState([]);

    useEffect(() => {
        stationService.getAllStations().then((res) => {
            console.log(res.data);

            setStationList(res.data);
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

                            <h1 class="h3 mb-2 text-gray-800">List Stations</h1>


                            {/* DataTales Example */}

                            <div class="card shadow mb-4">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Station Id</th>
                                                    <th>Name</th>
                                                    <th>Location</th>

                                                </tr>
                                            </thead>

                                            <tbody>
                                                {

                                                    stationList.map((e) => (
                                                        <tr>
                                                            <td key={e.stationId}>{e.stationId}</td>
                                                            <td key={e.stationName}>{e.stationName}</td>
                                                            <td key={e.stationLocation}>{e.stationLocation}</td>
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

export default ListStation