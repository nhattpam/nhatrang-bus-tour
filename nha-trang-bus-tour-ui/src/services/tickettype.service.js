import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class TicketTypeService{
    

    token = '';

    setToken(token) {
        this.token = token;
    }
    
    getAllTicketTypes()
    {
        return axios.get(BASE_API_URL + "/tickettypes/", {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });

    }

    saveTicketType(tickettype)
    {
        return axios.post(BASE_API_URL + "/tickettypes/", tickettype, {
            headers: {
                Authorization: `Bearer ${this.token}` // Include the bearer token in the headers
            }
        });
    }
}

export default new TicketTypeService();