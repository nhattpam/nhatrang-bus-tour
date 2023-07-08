import axios from "axios";


const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class TicketTypeService{
    
    getAllTicketTypes()
    {
        return axios.get(BASE_API_URL + "/tickettypes/");

    }

    saveTicketType(tickettype)
    {
        return axios.post(BASE_API_URL + "/tickettypes/", tickettype);
    }
}

export default new TicketTypeService();