import React from 'react'
import App from '../../App'
import Header from "../Header";
import SideBar from "../SideBar";
const ListUsers = () => {
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
            List Users
          </div>

        </div>
      </div>
    </div>

  </>
  )
}

export default ListUsers