import React, { useEffect, useState } from 'react'
import Header from '../Header'
import SideBar from '../SideBar'
import userService from '../../services/user.service';
const ListUsers = () => {

    const [userList, setUserList] = useState([]);

    useEffect(() => {
        userService.getAllUsers().then((res) => {
            console.log(res.data);

            setUserList(res.data);
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

                            <h1 class="h3 mb-2 text-gray-800">List Users</h1>


                            {/* DataTales Example */}

                            <div class="card shadow mb-4">
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>User Id</th>
                                                    <th>Email</th>
                                                    <th>Name</th>
                                                    <th>Phone</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                {

                                                    userList.map((e) => (
                                                        <tr>
                                                            <td key={e.userId}>{e.userId}</td>
                                                            <td key={e.userEmail}>{e.userEmail}</td>
                                                            <td key={e.userName}>{e.userName}</td>
                                                            <td key={e.userPhone}>{e.userPhone}</td>
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

export default ListUsers