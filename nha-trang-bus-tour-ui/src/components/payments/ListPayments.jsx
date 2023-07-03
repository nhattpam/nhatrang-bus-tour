import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import paymentService from '../../services/payment.service';

const ListPayments = () => {
    const [paymentList, setPaymentList] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
        paymentService.getAllPayments().then((res) => {
            console.log(res.data);

            setPaymentList(res.data);
        }).catch((error) => {
            console.log(error);
            console.log('lol');
        });
    }, []);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
    };

    const filteredPayments = paymentList.filter((payment) => {
        return (
            payment.paymentId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            payment.paymentDate.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
            payment.paymentMethod.toString().toLowerCase().includes(searchTerm.toLowerCase())
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

                            <h1 class="h3 mb-2 text-gray-800">List Payments</h1>

                            {/* Search Input */}
                            <div className="mb-3">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Search Payments"
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
                                                    <th>Payment Id</th>
                                                    <th>Payment Date</th>
                                                    <th>Payment Method</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {

                                                    filteredPayments.map((payment) => (
                                                        <tr key={payment.paymentId}>
                                                            <td>{payment.paymentId}</td>
                                                            <td>{payment.paymentDate}</td>
                                                            <td>{payment.paymentMethod}</td>
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

export default ListPayments