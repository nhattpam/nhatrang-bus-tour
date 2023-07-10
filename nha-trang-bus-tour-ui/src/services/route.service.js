import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class RouteService{

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllRoutes()
    {
        return axios.get(BASE_API_URL + "/routes/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    saveRoute(route)
    {
        return axios.post(BASE_API_URL + "/routes/", route, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new RouteService();