import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import driverService from '../../services/driver.service';
import ReactPaginate from 'react-paginate';

const ListDrivers = () => {
  const [driverList, setDriverList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [driversPerPage] = useState(5);

  useEffect(() => {
    driverService
      .getAllDrivers()
      .then((res) => {
        console.log(res.data);
        setDriverList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredDrivers = driverList.filter((driver) => {
    return (
      driver.driverId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      driver.driverName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      driver.driverPhone.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredDrivers.length / driversPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * driversPerPage;
  const currentDrivers = filteredDrivers.slice(offset, offset + driversPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Drivers</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Drivers"
                  value={searchTerm}
                  onChange={handleSearch}
                />
              </div>
              {/* DataTales Example */}
              <div className="card shadow mb-4">
                <div className="card-body">
                  <div className="table-responsive">
                    <table className="table table-bordered" id="dataTable" width="100%" cellSpacing="0">
                      <thead>
                        <tr>
                          <th>Driver Id</th>
                          <th>Driver Name</th>
                          <th>Driver Phone</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentDrivers.map((driver) => (
                          <tr key={driver.driverId}>
                            <td>{driver.driverId}</td>
                            <td>{driver.driverName}</td>
                            <td>{driver.driverPhone}</td>
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

export default ListDrivers;
