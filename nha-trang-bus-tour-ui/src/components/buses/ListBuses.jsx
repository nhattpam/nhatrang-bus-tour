import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import busService from '../../services/bus.service';
import { Link } from 'react-router-dom';


const ListBuses = () => {
    const [busList, setBusList] = useState([]);

    useEffect(() => {
        busService.getAllBuses().then((res) => {
            console.log(res.data);

            setBusList(res.data);
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

                            <h1 class="h3 mb-2 text-gray-800">List Buses</h1>


                            {/* DataTales Example */}

                            <div class="card shadow mb-4">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Bus Id</th>
                                                    <th>Bus Number</th>
                                                    <th>Seat</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {

                                                    busList.map((e) => (
                                                        <tr>
                                                            <td key={e.busId}>{e.busId}</td>
                                                            <td key={e.busNumber}>{e.busNumber}</td>
                                                            <td key={e.seat}>{e.seat}</td>
                                                            <td>
                                                                <div className="btn-group" role="group">
                                                                    <Link className="btn btn-primary" to={`/edit_bus/${e.busId}`}>
                                                                        Edit
                                                                    </Link>
                                                                    <Link className="btn btn-danger" to={`/delete_bus/${e.busId}`}>
                                                                        Delete
                                                                    </Link>
                                                                </div>
                                                            </td>

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

export default ListBuses