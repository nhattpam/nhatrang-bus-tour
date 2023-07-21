import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import ticketService from '../../services/ticket.service';
import Header from '../Header';
import SideBar from '../SideBar';
const OrderDetail = () => {
    const [ticketList, setTicketList] = useState([]);

    const { orderId } = useParams();

    console.log(orderId);

    useEffect(() => {
        if (orderId) {
            ticketService
                .getAllTicketsByOrderId(orderId)
                .then((res) => {
                    setTicketList(res.data);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }, []);


    return (
        <>
          {/* Page Wrapper */}
          <div id="wrapper">
            <SideBar />
            {/* Content Wrapper */}
            <div id="content-wrapper" className="d-flex flex-column">
              {/* Main Content */}
              <div id="content">
                <Header />
                <div className="container-fluid">
                  {/* Page Heading */}
                  <h1 className="h3 mb-2 text-gray-800">Order Detail</h1>
    
                  {/* Search Input */}
                  <div className="mb-3">
                  </div>
                  {/* DataTales Example */}
                  <div className="card shadow mb-4">
                    <div className="card-body">
                      <div className="table-responsive">
                        <table className="table table-bordered" id="" width="100%" cellSpacing="0">
                          <thead>
                            <tr>
                              <th>Ticket Id</th>
                              <th>Passenger</th>
                              <th>Phone </th>
                            </tr>
                          </thead>
                          <tbody>
                            {ticketList.map((ticket) => (
                              <tr key={ticket.ticketId}>
                                <td>{ticket.ticketId}</td>
                                <td>{ticket.passengerName}</td>
                                <td>{ticket.passengerPhone}</td>
                              </tr>
                            ))}
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
      );
}

export default OrderDetail