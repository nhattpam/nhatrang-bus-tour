import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class StationService{
    
    getAllStations()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/stations/");

    }

    saveStation(station)
    {
        return axios.post(BASE_API_URL + "/stations/", station);
    }
}

export default new StationService();