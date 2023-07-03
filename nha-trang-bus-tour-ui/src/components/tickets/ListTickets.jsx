import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import ticketService from '../../services/ticket.service';

const ListTickets = () => {
  const [ticketList, setTicketList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    ticketService.getAllTickets().then((res) => {
      console.log(res.data);

      setTicketList(res.data);
    }).catch((error) => {
      console.log(error);
      console.log('lol');
    });
  }, []);


  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredTickets = ticketList.filter((ticket) => {
    return (
      ticket.ticketId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      ticket.passengerEmail.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      ticket.passengerName.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      ticket.passengerPhone.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      ticket.feedback.toString().toLowerCase().includes(searchTerm.toLowerCase())
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

              <h1 class="h3 mb-2 text-gray-800">List Tickets</h1>

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

                          filteredTickets.map((ticket) => (
                            <tr key={ticket.ticketId}>
                              <td>{ticket.ticketId}</td>
                              <td>{ticket.passengerEmail}</td>
                              <td>{ticket.passengerName}</td>
                              <td>{ticket.passengerPhone}</td>
                              <td>{ticket.feedback}</td>
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
