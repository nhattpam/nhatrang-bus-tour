import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class TicketService{
    
    getAllTickets()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/tickets/");

    }

    saveTicket(ticket)
    {
        return axios.post(BASE_API_URL + "/tickets/", ticket);
    }
}

export default new TicketService();