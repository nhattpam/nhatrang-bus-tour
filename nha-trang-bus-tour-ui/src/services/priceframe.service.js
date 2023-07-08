import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class PriceFrameService{
    
    getAllPriceFrames()
    {
        return axios.get(BASE_API_URL + "/priceframes/");

    }

    savePriceFrame(priceframe)
    {
        return axios.post(BASE_API_URL + "/priceframes/", priceframe);
    }
}

export default new PriceFrameService();