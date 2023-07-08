import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import ReactPaginate from 'react-paginate';
import priceFrameTikcetService from '../../services/priceframeticket.service';

const ListPriceFrameTickets = () => {
  const [priceFrameTicketList, setPriceFrameTicketList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [priceFrameTicketsPerPage] = useState(5);

  useEffect(() => {
    priceFrameTikcetService
      .getAllPriceFrameTickets()
      .then((res) => {
        console.log(res.data);
        setPriceFrameTicketList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredPriceFrameTickets = priceFrameTicketList.filter((priceFrameTicket) => {
    return (
        priceFrameTicket.priceFrameTicketId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
        priceFrameTicket.price.toString().toLowerCase().includes(searchTerm.toLowerCase()) 
    );
  });

  const pageCount = Math.ceil(filteredPriceFrameTickets.length / priceFrameTicketsPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * priceFrameTicketsPerPage;
  const currentPriceFrameTickets = filteredPriceFrameTickets.slice(offset, offset + priceFrameTicketsPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Price Frames</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Price Frame Tickets"
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
                          <th>Price Frame Ticket Id</th>
                          <th>Price</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentPriceFrameTickets.map((priceFrameTicket) => (
                          <tr key={priceFrameTicket.priceFrameTicketId}>
                            <td>{priceFrameTicket.priceFrameTicketId}</td>
                            <td>{priceFrameTicket.price}</td>
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

export default ListPriceFrameTickets;
