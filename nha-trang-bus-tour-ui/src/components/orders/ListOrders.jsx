import React from 'react'
import Header from "../Header";
import SideBar from "../SideBar";

const ListOrders = () => {
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
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Order Id</th>
                                            <th>Order Date</th>
                                            <th>Payment</th>
                                            <th>User Id</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Tiger Nixon</td>
                                            <td>System Architect</td>
                                            <td>2011/04/25</td>
                                            <td>$320,800</td>
                                        </tr>
                                        <tr>
                                            <td>Donna Snider</td>
                                            <td>Customer Support</td>
                                            <td>2011/01/25</td>
                                            <td>$112,000</td>
                                        </tr>
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