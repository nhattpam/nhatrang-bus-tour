import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import paymentService from '../../services/payment.service';

const ListPayments = () => {
    const [paymentList, setPaymentList] = useState([]);

    useEffect(() => {
        paymentService.getAllPayments().then((res) => {
            console.log(res.data);

            setPaymentList(res.data);
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

                            <h1 class="h3 mb-2 text-gray-800">List Payments</h1>


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

                                                    paymentList.map((e) => (
                                                        <tr>
                                                            <td key={e.paymentId}>{e.paymentId}</td>
                                                            <td key={e.paymentDate}>{e.paymentDate}</td>
                                                            <td key={e.paymentMethod}>{e.paymentMethod}</td>
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