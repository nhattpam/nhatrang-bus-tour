import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import paymentService from '../../services/payment.service';
import ReactPaginate from 'react-paginate';

const ListPayments = () => {
  const [paymentList, setPaymentList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [paymentsPerPage] = useState(5);

  useEffect(() => {
    paymentService
      .getAllPayments()
      .then((res) => {
        console.log(res.data);
        setPaymentList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredPayments = paymentList.filter((payment) => {
    return (
      payment.paymentId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      payment.paymentDate.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      payment.paymentMethod.toString().toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredPayments.length / paymentsPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * paymentsPerPage;
  const currentPayments = filteredPayments.slice(offset, offset + paymentsPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Payments</h1>

              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Payments"
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
                          <th>Payment Id</th>
                          <th>Payment Date</th>
                          <th>Payment Method</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentPayments.map((payment) => (
                          <tr key={payment.paymentId}>
                            <td>{payment.paymentId}</td>
                            <td>{payment.paymentDate}</td>
                            <td>{payment.paymentMethod}</td>
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

export default ListPayments;
