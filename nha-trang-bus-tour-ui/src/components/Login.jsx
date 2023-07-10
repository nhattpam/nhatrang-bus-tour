import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import busService from '../services/bus.service';
import driverService from '../services/driver.service';
import orderService from '../services/order.service';
import paymentService from '../services/payment.service';
import priceframeService from '../services/priceframe.service';
import priceframeticketService from '../services/priceframeticket.service';
import routeService from '../services/route.service';
import servService from '../services/serv.service';
import stationService from '../services/station.service';
import ticketService from '../services/ticket.service';
import tickettypeService from '../services/tickettype.service';
import tripService from '../services/trip.service';
import userService from '../services/user.service';

const Login = ({ setIsLoggedIn }) => { // Add setIsLoggedIn prop
    const [email, setEmail] = useState('');
    const [email1, setEmail1] = useState('');
    const [token, setBearerToken] = useState('');
    const navigate = useNavigate();

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            let modifiedEmail = email;
            if (email === 'nhatrangbus@gmail.com') {
                modifiedEmail = 'nhatrangbus%40gmail.com';
                // setEmail1('nhatrangbus%40gmail.com');
                console.log(modifiedEmail)
            } else {
                modifiedEmail = email;
            }
            const response = await axios.post(`https://nhatrangbustourbackend.azurewebsites.net/api/users/login?email=${modifiedEmail}`);

            //token
            console.log('Token hello:', response.data);
            // Set the bearer token in the parent component
            setBearerToken(token);

            // Pass the token to the module
            busService.setToken(response.data);
            driverService.setToken(response.data);
            orderService.setToken(response.data);
            paymentService.setToken(response.data);
            priceframeService.setToken(response.data);
            priceframeticketService.setToken(response.data);
            routeService.setToken(response.data);
            servService.setToken(response.data);
            routeService.setToken(response.data);
            stationService.setToken(response.data);
            ticketService.setToken(response.data);
            tickettypeService.setToken(response.data);
            tripService.setToken(response.data);
            userService.setToken(response.data);


            if (modifiedEmail === 'nhatrangbus%40gmail.com') {
                setIsLoggedIn(true); // Update the isLoggedIn state
                navigate('/home');
            } else {
                setIsLoggedIn(false); // Update the isLoggedIn state
                navigate('/login');
            }
        } catch (error) {
            console.log('Login failed:', error);
        }
    };

    return (
        <div className="container mt-20">
            <div className="row justify-content-center mt-20">
                <div className="col-md-3 ">
                    <div className="card">
                        <div className="card-body">
                            <h2 className="card-title text-center">Login</h2>
                            <form onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <label htmlFor="email">Email:</label>
                                    <input
                                        type="email"
                                        className="form-control"
                                        id="email"
                                        value={email}
                                        onChange={handleEmailChange}
                                        placeholder="Enter your email"
                                        required
                                    />
                                </div>
                                <div className="text-center">
                                    <button type="submit" className="btn btn-primary">Login</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    );
};

export default Login;
