import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import stationService from '../../services/station.service';
import ReactPaginate from 'react-paginate';

const ListStation = () => {
  const [stationList, setStationList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [stationsPerPage] = useState(5);

  useEffect(() => {
    stationService
      .getAllStations()
      .then((res) => {
        console.log(res.data);
        setStationList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredStations = stationList.filter((station) => {
    return (
      station.stationId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      station.stationName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      station.stationLocation.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredStations.length / stationsPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * stationsPerPage;
  const currentStations = filteredStations.slice(offset, offset + stationsPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Stations</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Stations"
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
                          <th>Station Id</th>
                          <th>Name</th>
                          <th>Location</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentStations.map((station) => (
                          <tr key={station.stationId}>
                            <td>{station.stationId}</td>
                            <td>{station.stationName}</td>
                            <td>{station.stationLocation}</td>
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

export default ListStation;
