import axios from "axios";


const BASE_API_URL = "http://localhost:8080/api"
// const BASE_API_URL = "https://api.sampleapis.com/beers";
class BusService{
    
    getAllBuses()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/buses/");

    }

    saveBus(bus)
    {
        return axios.post(BASE_API_URL + "/buses/", bus);
    }
}

export default new BusService();