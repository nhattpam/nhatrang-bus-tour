import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import ticketService from '../../services/ticket.service';
import ReactPaginate from 'react-paginate';

const ListTickets = () => {
  const [ticketList, setTicketList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [ticketsPerPage] = useState(10);

  useEffect(() => {
    ticketService
      .getAllTickets()
      .then((res) => {
        console.log(res.data);
        setTicketList(res.data);
      })
      .catch((error) => {
        console.log(error);
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

  const pageCount = Math.ceil(filteredTickets.length / ticketsPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * ticketsPerPage;
  const currentTickets = filteredTickets.slice(offset, offset + ticketsPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Tickets</h1>
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
              <div className="card shadow mb-4">
                <div className="card-body">
                  <div className="table-responsive">
                    <table className="table table-bordered" id="" width="100%" cellSpacing="0">
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
                        {currentTickets.map((ticket) => (
                          <tr key={ticket.ticketId}>
                            <td>{ticket.ticketId}</td>
                            <td>{ticket.passengerEmail}</td>
                            <td>{ticket.passengerName}</td>
                            <td>{ticket.passengerPhone}</td>
                            <td>{ticket.feedback}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <ReactPaginate
                previousLabel={'Previous'}
                nextLabel={'Next'}
                breakLabel={'...'}
                breakClassName={'page-item'}
                breakLinkClassName={'page-link'}
                pageCount={pageCount}
                marginPagesDisplayed={2}
                pageRangeDisplayed={5}
                onPageChange={handlePageClick}
                containerClassName={'pagination'}
                activeClassName={'active'}
                previousClassName={'page-item'}
                nextClassName={'page-item'}
                pageClassName={'page-item'}
                previousLinkClassName={'page-link'}
                nextLinkClassName={'page-link'}
                pageLinkClassName={'page-link'}
              />
            </div>
            {/* /.container-fluid */}
          </div>
        </div>
      </div>
    </>
  );
};

export default ListTickets;
