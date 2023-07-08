import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class ServService{
    
    getAllServices()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/services/");

    }

    saveService(service)
    {
        return axios.post(BASE_API_URL + "/services/", service);
    }
}

export default new ServService();