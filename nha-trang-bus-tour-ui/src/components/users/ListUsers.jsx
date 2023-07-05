import React, { useEffect, useState } from 'react';
import Header from '../Header';
import SideBar from '../SideBar';
import userService from '../../services/user.service';
import ReactPaginate from 'react-paginate';

const ListUsers = () => {
  const [userList, setUserList] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const [usersPerPage] = useState(5);

  useEffect(() => {
    userService
      .getAllUsers()
      .then((res) => {
        console.log(res.data);
        setUserList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const handleSearch = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredUsers = userList.filter((user) => {
    return (
      user.userId.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.userEmail.toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.userName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      user.userPhone.toLowerCase().includes(searchTerm.toLowerCase())
    );
  });

  const pageCount = Math.ceil(filteredUsers.length / usersPerPage);

  const handlePageClick = (data) => {
    setCurrentPage(data.selected);
  };

  const offset = currentPage * usersPerPage;
  const currentUsers = filteredUsers.slice(offset, offset + usersPerPage);

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
              <h1 className="h3 mb-2 text-gray-800">List Users</h1>
              {/* Search Input */}
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Search Users"
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
                          <th>User Id</th>
                          <th>Email</th>
                          <th>Name</th>
                          <th>Phone</th>
                        </tr>
                      </thead>
                      <tbody>
                        {currentUsers.map((user) => (
                          <tr key={user.userId}>
                            <td>{user.userId}</td>
                            <td>{user.userEmail}</td>
                            <td>{user.userName}</td>
                            <td>{user.userPhone}</td>
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

export default ListUsers;
