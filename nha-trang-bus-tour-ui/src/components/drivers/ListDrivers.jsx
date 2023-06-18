import React, { useEffect, useState } from 'react'
import Header from '../Header'
import SideBar from '../SideBar'
import driverService from '../../services/driver.service';

const ListDrivers = () => {

    const [driverList, setDriverList] = useState([]);

    useEffect(() => {
        driverService.getAllDrivers().then((res) => {
            console.log(res.data);

            setDriverList(res.data);
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

                            <h1 class="h3 mb-2 text-gray-800">List Drivers</h1>


                            {/* DataTales Example */}

                            <div class="card shadow mb-4">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Driver Id</th>
                                                    <th>Driver Name</th>
                                                    <th>Driver Phone</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                {

                                                    driverList.map((e) => (
                                                        <tr>
                                                            <td key={e.driverId}>{e.driverId}</td>
                                                            <td key={e.driverName}>{e.driverName}</td>
                                                            <td key={e.driverPhone}>{e.driverPhone}</td>
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

export default ListDrivers