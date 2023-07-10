import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class PriceFrameService{

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllPriceFrames()
    {
        return axios.get(BASE_API_URL + "/priceframes/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    savePriceFrame(priceframe)
    {
        return axios.post(BASE_API_URL + "/priceframes/", priceframe, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new PriceFrameService();