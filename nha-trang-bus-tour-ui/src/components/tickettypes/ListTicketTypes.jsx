import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import ReactPaginate from 'react-paginate';
import ticketTypeService from '../../services/tickettype.service';

const ListTicketTypes = () => {
  const [ticketTypeList, setTicketTypeList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [ticketTypesPerPage] = useState(5);

  useEffect(() => {
    ticketTypeService
      .getAllTicketTypes()
      .then((res) => {
        console.log(res.data);
        setTicketTypeList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredTicketTypes = ticketTypeList.filter((ticketType) => {
    return (
        ticketType.ticketTypeId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
        ticketType.ticketTypeName.toLowerCase().includes(searchTerm.toLowerCase()) 
    );
  });

  const pageCount = Math.ceil(filteredTicketTypes.length / ticketTypesPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * ticketTypesPerPage;
  const currentTicketTypes = filteredTicketTypes.slice(offset, offset + ticketTypesPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Ticket Types</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Ticket Types"
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
                          <th>Ticket Type Id</th>
                          <th>Name</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentTicketTypes.map((ticketType) => (
                          <tr key={ticketType.ticketTypeId}>
                            <td>{ticketType.ticketTypeId}</td>
                            <td>{ticketType.ticketTypeName}</td>
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

export default ListTicketTypes;
