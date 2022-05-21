import axios from "axios";

const instance = axios.create({
  baseURL: 'http://localhost:8080/pa165/rest',
});

export default instance;
