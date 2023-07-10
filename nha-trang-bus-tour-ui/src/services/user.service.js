import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class UserService{

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllUsers()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/users/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    saveUser(user)
    {
        return axios.post(BASE_API_URL + "/users/", user, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new UserService();