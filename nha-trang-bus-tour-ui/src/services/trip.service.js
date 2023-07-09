import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class TripService{

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllTrips()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/trips/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    saveTrip(trip)
    {
        return axios.post(BASE_API_URL + "/trips/", trip, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new TripService();