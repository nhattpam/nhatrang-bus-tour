import React from 'react'
import Header from "../Header";
import SideBar from "../SideBar";
const ListTrips = () => {
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

                    <h1 class="h3 mb-2 text-gray-800">List Trips</h1>
                    

                   {/* DataTales Example */}

                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Trip Id</th>
                                            <th>Arrival Time</th>
                                            <th>Departure Time</th>
                                            <th>Bus</th>
                                            <th>Driver</th>
                                            <th>Price Frame</th>
                                            <th>Route</th>
                                        </tr>
                                    </thead>
                                  
                                    <tbody>
                                        <tr>
                                            <td>Tiger Nixon</td>
                                            <td>61</td>
                                            <td>2011/04/25</td>
                                            <td>$320,800</td>
                                            <td>2011/04/25</td>
                                            <td>$320,800</td>
                                            <td>$320,800</td>
                                        </tr>
                                        <tr>
                                            <td>Garrett Winters</td>
                                            <td>63</td>
                                            <td>2011/07/25</td>
                                            <td>$170,750</td>
                                            <td>$170,750</td>
                                            <td>$170,750</td>
                                            <td>$170,750</td>
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

export default ListTrips