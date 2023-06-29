import React from 'react'
import Header from '../Header'
import SideBar from '../SideBar'

const ListDrivers = () => {
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

                    <h1 class="h3 mb-2 text-gray-800">List Drivers</h1>
                    

                   {/* DataTales Example */}

                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Driver Id</th>
                                            <th>Driver Name</th>
                                            <th>Drive Phone</th>
                                        </tr>
                                    </thead>
                                    
                                    <tbody>
                                        <tr>
                                            <td>Tiger Nixon</td>
                                            <td>System Architect</td>
                                            <td>61</td>
                                        </tr>
                                        <tr>
                                            <td>Garrett Winters</td>
                                            <td>Accountant</td>
                                            <td>63</td>
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

export default ListDrivers