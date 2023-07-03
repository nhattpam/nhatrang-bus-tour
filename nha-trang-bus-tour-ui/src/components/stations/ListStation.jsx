import React, { useEffect, useState } from 'react'
import Header from '../Header'
import SideBar from '../SideBar'
import stationService from '../../services/station.service';

const ListStation = () => {


    const [stationList, setStationList] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        stationService.getAllStations().then((res) => {
            console.log(res.data);

            setStationList(res.data);
        }).catch((error) => {
            console.log(error);
            console.log('lol');
        });
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredStations = stationList.filter((station) => {
        return (
            station.stationId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            station.stationName.toLowerCase().includes(searchTerm.toLowerCase()) ||
            station.stationLocation.toLowerCase().includes(searchTerm.toLowerCase())
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

                            <h1 class="h3 mb-2 text-gray-800">List Stations</h1>
                            {/* Search Input */}
                            <div className="mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Search Stations"
                                    value={searchTerm}
                                    onChange={handleSearch}
                                />
                            </div>

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

                                                    filteredStations.map((station) => (
                                                        <tr key={station.stationId}>
                                                            <td>{station.stationId}</td>
                                                            <td>{station.stationName}</td>
                                                            <td>{station.stationLocation}</td>
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