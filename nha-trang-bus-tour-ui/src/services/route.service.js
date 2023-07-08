import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class RouteService{
    
    getAllRoutes()
    {
        return axios.get(BASE_API_URL + "/routes/");

    }

    saveRoute(route)
    {
        return axios.post(BASE_API_URL + "/routes/", route);
    }
}

export default new RouteService();