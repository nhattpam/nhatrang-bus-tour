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
              List Buses
            </div>

          </div>
        </div>
      </div>

    </>
      )
}

export default ListDrivers