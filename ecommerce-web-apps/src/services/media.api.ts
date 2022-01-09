import axios from "axios";

const mediaApi = axios.create({
    baseURL : "http://localhost:8081/api",
    headers: {
        'Content-Type': 'multipart,/form-data'
    }
});

export default mediaApi