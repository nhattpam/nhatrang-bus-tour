import axios from "axios";


const BASE_API_URL = "http://nhatrangbustourbackend-env.eba-ksxs24vb.ap-northeast-1.elasticbeanstalk.com/api";
// const BASE_API_URL = "https://api.sampleapis.com/beers";
class BusService{
    
    getAllBuses()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/buses/");

    }
}

export default new BusService();