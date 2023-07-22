import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import orderService from '../../services/order.service';
import { Link } from 'react-router-dom';
import ReactPaginate from 'react-paginate';

const ListOrders = () => {
  const [orderList, setOrderList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [ordersPerPage] = useState(5);

  useEffect(() => {
    orderService
      .getAllOrders()
      .then((res) => {
        console.log(res.data);
        setOrderList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredOrders = orderList.filter((order) => {
    return (
      order.orderId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      order.orderDate.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      order.payment.paymentMethod.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      order.userId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      order.totalprice.toString().toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredOrders.length / ordersPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * ordersPerPage;
  const currentOrders = filteredOrders.slice(offset, offset + ordersPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Orders</h1>

              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Orders"
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
                          <th>Order Id</th>
                          <th>Order Date</th>
                          <th>Total Price</th>
                          <th>Payment Method</th>
                          <th>User Id</th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentOrders.map((order) => (
                          <tr key={order.orderId}>
                            <td>{order.orderId}</td>
                            <td>{order.orderDate}</td>
                            <td>{order.totalprice} $</td>
                            <td>{order.payment.paymentMethod}</td>
                            <td>{order.userId}</td>
                            <div className="btn-group" role="group">
                              <Link className="btn btn-primary" to={`/order_detail/${order.orderId}`}>
                                Detail
                              </Link>
                              {/* <a className="btn btn-danger" onClick={() => deleteBus(e.busId)}>
                                  Delete
                                </a> */}
                            </div>
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

export default ListOrders;
