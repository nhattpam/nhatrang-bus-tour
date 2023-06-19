import axios from "axios";


const BASE_API_URL = "http://localhost:8080/api"
// const BASE_API_URL = "https://api.sampleapis.com/beers";
class BusService{
    
    getAllBuses()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/buses/");

    }

    saveBus(bus)
    {
        return axios.post(BASE_API_URL + "/buses/", bus);
    }

    updateBus(id, bus){
        return axios.put(BASE_API_URL + "/buses/"+ id, bus);
    }
    

    getBusById(id){
        return axios.get(BASE_API_URL + "/buses/"+ id);
    }

    deleteBus(id){
        return axios.delete(BASE_API_URL + "/buses/"+ id);
    }
}

export default new BusService();