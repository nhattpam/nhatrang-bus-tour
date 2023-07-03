import axios from "axios";


// const BASE_API_URL = "http://localhost:8080/api"
const BASE_API_URL = "https://nhatrangbustourbackend.azurewebsites.net/api"
class PaymentService{
    
    getAllPayments()
    {
        // return axios.get(BASE_API_URL + "/ale");
        return axios.get(BASE_API_URL + "/payments/");

    }

    savePayment(payment)
    {
        return axios.post(BASE_API_URL + "/payments/", payment);
    }
}

export default new PaymentService();