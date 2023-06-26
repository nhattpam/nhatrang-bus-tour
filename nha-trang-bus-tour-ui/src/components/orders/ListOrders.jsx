import React, { useEffect, useState } from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
import orderService from '../../services/order.service';
import { Link } from 'react-router-dom';

const ListOrders = () => {

  const [orderList, setOrderList] = useState([]);

  useEffect(() => {
    orderService.getAllOrders().then((res) => {
      console.log(res.data);

      setOrderList(res.data);
    }).catch((error) => {
      console.log(error);
      console.log('lol');
    });
  }, []);

  return (
    <>
      {/* Page Wrapper */}

      <div id="wrapper">
        <SideBar />
        {/* Content Wrapper */}

        <div id="content-wrapper" class="d-flex flex-column">
          {/* Main Content */}

          <div id="content">
            <Header />
            <div class="container-fluid">

              {/* Page Heading */}

              <h1 class="h3 mb-2 text-gray-800">List Orders</h1>


              {/* DataTales Example */}

              <div class="card shadow mb-4">
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-bordered" id="" width="100%" cellspacing="0">
                      <thead>
                        <tr>
                          <th>Order Id</th>
                          <th>Order Date</th>
                          <th>Payment Method</th>
                          <th>User Id</th>

                        </tr>
                      </thead>
                      <tbody>
                        {

                          orderList.map((e) => (
                            <tr>
                              <td key={e.orderId}>{e.orderId}</td>
                              <td key={e.orderDate}>{e.orderDate}</td>
                              <td key={e.payment.paymentMethod}>{e.payment.paymentMethod}</td>
                              <td key={e.userId}>{e.userId}</td>
                            </tr>
                          ))

                        }

                      </tbody>
                    </table>
                  </div>
                </div>
              </div>

            </div>
            {/* /.container-fluid */}


          </div>
        </div>
      </div>

    </>
  )
}

export default ListOrders