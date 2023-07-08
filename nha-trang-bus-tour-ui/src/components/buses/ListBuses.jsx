import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import busService from '../../services/bus.service';
import { Link } from 'react-router-dom';
import ReactPaginate from 'react-paginate';

const ListBuses = () => {
  const [busList, setBusList] = useState([]);
  const [msg, setMsg] = useState('');
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [busesPerPage] = useState(5);

  useEffect(() => {
    busService
      .getAllBuses()
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

  const pageCount = Math.ceil(filteredBuses.length / busesPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * busesPerPage;
  const currentBuses = filteredBuses.slice(offset, offset + busesPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Buses</h1>

              {msg && <p className="text-center text-success">{msg}</p>}

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
                          <th>Bus Id</th>
                          <th>Bus Number</th>
                          <th>Seat</th>
                          <th>Action</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentBuses.map((bus) => (
                          <tr key={bus.busId}>
                            <td>{bus.busId}</td>
                            <td>{bus.busNumber}</td>
                            <td>{bus.seat}</td>
                            <td>
                              <div className="btn-group" role="group">
                                <Link className="btn btn-primary" to={`/edit_bus/${bus.busId}`}>
                                  Edit
                                </Link>
                                {/* <a className="btn btn-danger" onClick={() => deleteBus(e.busId)}>
                                  Delete
                                </a> */}
                              </div>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>

              {/* Pagination */}
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

export default ListBuses;
