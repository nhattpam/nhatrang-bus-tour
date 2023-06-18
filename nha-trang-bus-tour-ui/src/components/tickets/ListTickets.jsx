import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import ticketService from '../../services/ticket.service';

const ListTickets = () => {
  const [ticketList, setTicketList] = useState([]);

  useEffect(() => {
    ticketService.getAllTickets().then((res) => {
      console.log(res.data);

      setTicketList(res.data);
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

              <h1 class="h3 mb-2 text-gray-800">List Tickets</h1>


              {/* DataTales Example */}

              <div class="card shadow mb-4">
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                      <thead>
                        <tr>
                          <th>Ticket Id</th>
                          <th>Passenger Email</th>
                          <th>Passenger Name</th>
                          <th>Passenger Phone</th>
                          <th>Feedback</th>
                        </tr>
                      </thead>

                      <tbody>
                        {

                          ticketList.map((e) => (
                            <tr>
                              <td key={e.ticketId}>{e.ticketId}</td>
                              <td key={e.passengerEmail}>{e.passengerEmail}</td>
                              <td key={e.passengerName}>{e.passengerName}</td>
                              <td key={e.passengerPhone}>{e.passengerPhone}</td>
                              <td key={e.feedback}>{e.feedback}</td>
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
  );
};

export default ListTickets;
