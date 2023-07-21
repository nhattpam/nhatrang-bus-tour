import { Link } from "react-router-dom"

const SideBar = () => {
    return (
        <>
            {/* Sidebar */}
            <ul className="navbar-nav bg-gradient-dark sidebar sidebar-dark accordion" id="accordionSidebar">
                {/* Sidebar - Brand */}
                <Link className="sidebar-brand d-flex align-items-center justify-content-center" to="/home">
                    <div className="sidebar-brand-icon ">
                       <i class="fas fa-user-cog"></i>
                    </div>
                    <div className="sidebar-brand-text mx-3">Admin</div>
                </Link>
                {/* Divider */}
                <hr className="sidebar-divider my-0" />
                {/* Nav Item - Dashboard */}
                <li className="nav-item active">
                    <Link className="nav-link" to="/home">
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
                        <i className="fas fa-box" />
                        <span>Order </span>
                    </a>
                    <div id="collapseUtilities" className="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_orders">List Orders</Link>

                        </div>
                    </div>

                </li>
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilitie" aria-expanded="true" aria-controls="collapseUtilitie">
                        <i className="fas fa-box" />
                        <span>Payment </span>
                    </a>
                    <div id="collapseUtilitie" className="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_payments">List Payments</Link>

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
                    <div id="collapseFeedbacks" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_trip">Add Trip</Link>
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
                    <div id="collapseTickets" className="collapse" aria-labelledby="headingTickets" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_bus">Add Bus</Link>
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
                    <div id="collapseStations" className="collapse" aria-labelledby="headingStation" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_station">Add Station</Link>
                        </div>
                    </div>
                </li>

                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseStationss" aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-route"></i>
                        <span>Route </span>
                    </a>
                    <div id="collapseStationss" className="collapse" aria-labelledby="headingStation" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_routes">List Routes</Link>
                        </div>
                    </div>
                    <div id="collapseStationss" className="collapse" aria-labelledby="headingStation" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_route">Add Route</Link>
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
                    <div id="collapseDrivers" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_driver">Add Driver</Link>
                        </div>
                    </div>
                </li>

                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDriverss" aria-expanded="true" aria-controls="collapsePages">
                        <i class="fas fa-sliders-h"></i>
                        <span>Service </span>
                    </a>
                    <div id="collapseDriverss" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_services">List Services</Link>
                        </div>
                    </div>
                    <div id="collapseDriverss" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_service">Add Service</Link>
                        </div>
                    </div>
                </li>
{/* 
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDriversss" aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-border-style"></i>
                        <span>Ticket Type </span>
                    </a>
                    <div id="collapseDriversss" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_tickettypes">List Ticket Types</Link>
                        </div>
                    </div>
                    <div id="collapseDriversss" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_tickettype">Add Ticket Type</Link>
                        </div>
                    </div>
                </li> */}

                {/* <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDriversss1" aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-hand-holding-usd"></i>
                        <span>Price Frame </span>
                    </a>
                    <div id="collapseDriversss1" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_priceframes">List Price Frames</Link>
                        </div>
                    </div>
                    <div id="collapseDriversss1" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_priceframe">Add Price Frame</Link>
                        </div>
                    </div>
                </li>

                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseDriversss2" aria-expanded="true" aria-controls="collapsePages">
                    <i class="far fa-money-bill-alt"></i>
                        <span>Price Frame Ticket</span>
                    </a>
                    <div id="collapseDriversss2" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/list_priceframetickets">List Price Frame Tickets</Link>
                        </div>
                    </div>
                    <div id="collapseDriversss2" className="collapse" aria-labelledby="headingFeedback" data-parent="#accordionSidebar">
                        <div className="bg-white py-2 collapse-inner rounded">
                            <Link className="collapse-item" to="/add_priceframe">Add Price Frame Ticket</Link>
                        </div>
                    </div>
                </li> */}

                {/* Nav Item - Pages Collapse Menu */}
                <li className="nav-item">
                    <a className="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                        <i className="fas fa-user" />
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