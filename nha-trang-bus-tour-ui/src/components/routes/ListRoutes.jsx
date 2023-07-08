import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import ReactPaginate from 'react-paginate';
import routeService from '../../services/route.service';

const ListRoutes = () => {
  const [routeList, setRouteList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [routesPerPage] = useState(5);

  useEffect(() => {
    routeService
      .getAllRoutes()
      .then((res) => {
        console.log(res.data);
        setRouteList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredRoutes = routeList.filter((route) => {
    return (
        route.routeId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
        route.routeName.toLowerCase().includes(searchTerm.toLowerCase()) ||
        route.parentRouteID.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredRoutes.length / routesPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * routesPerPage;
  const currentRoutes = filteredRoutes.slice(offset, offset + routesPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Routes</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Routes"
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
                          <th>Route Id</th>
                          <th>Name</th>
                          <th>Parent Route</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentRoutes.map((route) => (
                          <tr key={route.routeId}>
                            <td>{route.routeId}</td>
                            <td>{route.routeName}</td>
                            <td>{route.parentRouteID}</td>
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

export default ListRoutes;
