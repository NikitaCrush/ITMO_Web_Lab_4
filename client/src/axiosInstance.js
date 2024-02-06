import axios from 'axios';
import store from "./redux/store";
import {logoutAuth} from "./redux/actions/pointsActions";

const axiosInstance = axios.create();

axiosInstance.interceptors.response.use(
    response => response,
    error => {
        if (error.response && error.response.status === 403) {
            // Handle token expiration or invalid token
            console.log("Token is invalid or expired");
            store.dispatch(logoutAuth());
            localStorage.removeItem('jwtToken');
            window.location.href = "/"; // Redirect to login page
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;