import axios from "axios";


const BASE_API_URL = "http://localhost:8080/api"
// const BASE_API_URL = "https://api.sampleapis.com/beers";
class TripService{
    
    getAllTrips()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/trips/");

    }

    saveTrip(trip)
    {
        return axios.post(BASE_API_URL + "/trips/", trip);
    }
}

export default new TripService();