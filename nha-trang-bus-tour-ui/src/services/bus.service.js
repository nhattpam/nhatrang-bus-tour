import axios from "axios";

const BASE_API_URL = "http://nhatrangbustourbackend-env.eba-ksxs24vb.ap-northeast-1.elasticbeanstalk.com/api";

class BusService{
    
    getAllBuses()
    {
        return axios.get(BASE_API_URL + "/buses/");
    }
}

export default new BusService();