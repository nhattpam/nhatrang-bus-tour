import { Link } from "react-router-dom"

const SideBar = () => {
    return (
        <>
            {/* Sidebar */}
            <ul className="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                {/* Sidebar - Brand */}
                <Link className="sidebar-brand d-flex align-items-center justify-content-center" to="/">
                    <div className="sidebar-brand-icon rotate-n-15">
                        <i className="fas fa-laugh-wink" />
                    </div>
                    <div className="sidebar-brand-text mx-3">Admin</div>
                </Link>
                {/* Divider */}
                <hr className="sidebar-divider my-0" />
                {/* Nav Item - Dashboard */}
                <li className="nav-item active">
                    <Link className="nav-link" to="/">
                        <i className="fas fa-fw fa-tachometer-alt" />
                        <span>Dashboard</span></Link>
                </li>
                {/* Divider */}
                <hr className="sidebar-divider" />
                {/* Heading */}
                <div className="sidebar-heading">
                    Management
                </div>
                {/* Nav Item - Utilities Collapse Menu */}
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
                        <i className="fas fa-box"/>
                        <span>Order </span>
                    </a>
                    <div id="collapseUtilities" className="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_orders">List Orders</Link>

                        </div>
                    </div>

                </li>
                
                
                {/* Nav Item - Pages Collapse Menu */}
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
                        <i className="fas fa-ticket-alt" /><i class="fas fa-ticket"></i>
                        <span>Ticket </span>
                    </a>
                    <div id="collapsePages" className="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_tickets">List Tickets</Link>
                        </div>
                    </div>
                </li>
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseFeedbacks" aria-expanded="true" aria-controls="collapsePages">
                        <i className="fas fa-suitcase" />
                        <span>Trip </span>
                    </a>
                    <div id="collapseFeedbacks" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_trips">List Trips</Link>
                        </div>
                    </div>
                </li>

                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseStations" aria-expanded="true" aria-controls="collapsePages">
                        <i className="fas fa-home" />
                        <span>Station </span>
                    </a>
                    <div id="collapseStations" className="collapse" aria-labelledby="headingStation" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_stations">List Stations</Link>
                        </div>
                    </div>
                </li>
                
                {/* Nav Item - Charts */}
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTickets" aria-expanded="true" aria-controls="collapsePages">
                        <i className="fas fa-bus" />
                        <span>Bus </span>
                    </a>
                    <div id="collapseTickets" className="collapse" aria-labelledby="headingTickets" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_buses">List Buses</Link>
                        </div>
                    </div>
                </li>
                

                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDrivers" aria-expanded="true" aria-controls="collapsePages">
                        <i className="fas fa-male" />
                        <span>Driver </span>
                    </a>
                    <div id="collapseDrivers" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_drivers">List Drivers</Link>
                        </div>
                    </div>
                </li>
                {/* Nav Item - Pages Collapse Menu */}
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                    <i className="fas fa-user"/>
                        <span>User </span>
                    </a>
                    <div id="collapseTwo" className="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_users">List Users</Link>
                        </div>
                    </div>
                </li>


            </ul>
            {/* End of Sidebar */}
            


        </>
    )
}

export default SideBar