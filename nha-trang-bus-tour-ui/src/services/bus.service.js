import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"

// const BASE_API_URL = "https://api.sampleapis.com/beers";
class BusService {

    token = '';

    setToken(token) {
        this.token = token;
    }

    getAllBuses() {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/buses/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    saveBus(bus) {
        return axios.post(BASE_API_URL + "/buses/", bus, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }

    updateBus(id, bus) {
        return axios.put(BASE_API_URL + "/buses/" + id, bus, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }


    getBusById(id) {
        return axios.get(BASE_API_URL + "/buses/" + id, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }

    deleteBus(id) {
        return axios.delete(BASE_API_URL + "/buses/" + id, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new BusService();