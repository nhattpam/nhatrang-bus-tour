import axios from "axios";


const BASE_API_URL = "http://localhost:8080/api"

class DriverService{
    
    getAllDrivers()
    {
        return axios.get(BASE_API_URL + "/drivers/");

    }

    saveDriver(driver)
    {
        return axios.post(BASE_API_URL + "/drivers/", driver);
    }
}

export default new DriverService();