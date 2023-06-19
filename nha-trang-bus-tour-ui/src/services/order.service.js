import axios from "axios";


const BASE_API_URL = "http://localhost:8080/api"
// const BASE_API_URL = "https://api.sampleapis.com/beers";
class OrderService{
    
    getAllOrders()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/orders/");

    }

    saveOrder(order)
    {
        return axios.post(BASE_API_URL + "/orders/", order);
    }
}

export default new OrderService();