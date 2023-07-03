import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import busService from '../../services/bus.service';
import { Link } from 'react-router-dom';


const ListBuses = () => {
    const [busList, setBusList] = useState([]);
    const [msg, setMsg] = useState('');
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        busService.getAllBuses()
            .then((res) => {
                console.log(res.data);
                setBusList(res.data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredBuses = busList.filter((bus) => {
        return (
            bus.busId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            bus.busNumber.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            bus.seat.toString().toLowerCase().includes(searchTerm.toLowerCase())
        );
    });

    // const deleteBus = (busId) => {
    //     busService.deleteBus(busId).then((res) => {
    //         setMsg("Delete bus successfully!");
    //     }).catch((error) => {
    //         console.log(error);
    //     });
    // };

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

                            {
                                msg && <p className='text-center text-success'>{msg}</p>
                            }

                            {/* Search Input */}
                            <div className="mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Search Tickets"
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
                                                    <th>Bus Id</th>
                                                    <th>Bus Number</th>
                                                    <th>Seat</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {

                                                    filteredBuses.map((bus) => (
                                                        <tr key={bus.busId}>
                                                            <td key={bus.busId}>{bus.busId}</td>
                                                            <td key={bus.busNumber}>{bus.busNumber}</td>
                                                            <td key={bus.seat}>{bus.seat}</td>
                                                            <td>
                                                                <div className="btn-group" role="group">
                                                                    <Link className="btn btn-primary" to={"/edit_bus/" + bus.busId}>
                                                                        Edit
                                                                    </Link>
                                                                    {/* <a className="btn btn-danger" onClick={() => deleteBus(e.busId)}>
                                                                        Delete
                                                                    </a> */}
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