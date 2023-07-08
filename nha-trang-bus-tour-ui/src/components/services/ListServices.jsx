import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import ReactPaginate from 'react-paginate';
import servService from '../../services/serv.service';

const ListServices = () => {
  const [serviceList, setServiceList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [servicesPerPage] = useState(5);

  useEffect(() => {
    servService
      .getAllServices()
      .then((res) => {
        console.log(res.data);
        setServiceList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredServices = serviceList.filter((service) => {
    return (
        service.serviceId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
        service.serviceNumber.toLowerCase().includes(searchTerm.toLowerCase()) ||
        service.serviceName.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredServices.length / servicesPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * servicesPerPage;
  const currentServices = filteredServices.slice(offset, offset + servicesPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Services</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Services"
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
                          <th>Service Id</th>
                          <th>Number</th>
                          <th>Name</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentServices.map((service) => (
                          <tr key={service.serviceId}>
                            <td>{service.serviceId}</td>
                            <td>{service.serviceNumber}</td>
                            <td>{service.serviceName}</td>
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

export default ListServices;
