import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"

class DriverService{

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllDrivers()
    {
        return axios.get(BASE_API_URL + "/drivers/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    saveDriver(driver)
    {
        return axios.post(BASE_API_URL + "/drivers/", driver, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new DriverService();