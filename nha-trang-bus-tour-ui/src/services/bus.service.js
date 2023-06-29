import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
// const BASE_API_URL = "https://api.sampleapis.com/beers";
class BusService{
    
    getAllBuses()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/buses/");

    }
}

export default new BusService();