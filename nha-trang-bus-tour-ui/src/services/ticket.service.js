import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class TicketService{

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllTickets()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/tickets/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    saveTicket(ticket)
    {
        return axios.post(BASE_API_URL + "/tickets/", ticket, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new TicketService();