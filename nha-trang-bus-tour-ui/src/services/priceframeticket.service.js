import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class PriceFrameTicketService{
    
    getAllPriceFrameTickets()
    {
        return axios.get(BASE_API_URL + "/priceframetickets/");

    }

    savePriceFrameTicket(priceframeticket)
    {
        return axios.post(BASE_API_URL + "/priceframetickets/", priceframeticket);
    }
}

export default new PriceFrameTicketService();