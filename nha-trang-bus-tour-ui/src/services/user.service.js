import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class UserService{
    
    getAllUsers()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/users/");

    }

    saveUser(user)
    {
        return axios.post(BASE_API_URL + "/users/", user);
    }
}

export default new UserService();