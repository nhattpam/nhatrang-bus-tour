import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class PriceFrameTicketService{

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllPriceFrameTickets()
    {
        return axios.get(BASE_API_URL + "/priceframetickets/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    savePriceFrameTicket(priceframeticket)
    {
        return axios.post(BASE_API_URL + "/priceframetickets/", priceframeticket, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new PriceFrameTicketService();