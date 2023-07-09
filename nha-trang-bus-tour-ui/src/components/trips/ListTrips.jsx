import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import tripService from '../../services/trip.service';
import ReactPaginate from 'react-paginate';

const ListTrips = () => {
  const [tripList, setTripList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [tripsPerPage] = useState(5);

  useEffect(() => {
    tripService
      .getAllTrips()
      .then((res) => {
        console.log(res.data);
        setTripList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredTrips = tripList.filter((trip) => {
    return (
      trip.tripID.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.departureTime.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.arrivalTime.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.bus.busNumber.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.driver.driverName.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.priceFrame.priceFrameName.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      trip.route.routeName.toString().toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredTrips.length / tripsPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * tripsPerPage;
  const currentTrips = filteredTrips.slice(offset, offset + tripsPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Trips</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Trips"
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
                          <th>Trip Id</th>
                          <th>Departure Time</th>
                          <th>Arrival Time</th>
                          <th>Bus</th>
                          <th>Driver</th>
                          <th>Price Frame</th>
                          <th>Route</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentTrips.map((trip) => (
                          <tr key={trip.tripID}>
                            <td>{trip.tripID}</td>
                            <td>{trip.departureTime}</td>
                            <td>{trip.arrivalTime}</td>
                            <td>{trip.bus.busNumber}</td>
                            <td>{trip.driver.driverName}</td>
                            <td>{trip.priceFrame?.priceFrameName}</td>
                            <td>{trip.route.routeName}</td>
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

export default ListTrips;
