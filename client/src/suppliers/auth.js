import axiosInstance from "../axiosInstance";


export function useAuth() {
    const register = async (login, password) => {
        try {
            const response = await axiosInstance.post(
                'http://localhost:8080/api/register',
                {username: login, password: password},
                {headers: {'Content-Type': 'application/json'}}
            );
            console.log('Registration successful', response.data);
            return response.data;
        } catch (error) {
            console.error('Registration failed:', error.response.data);
            throw new Error(error.response.data);
        }
    };

    const login = async (login, password) => {
        try {
            const response = await axiosInstance.post(
                'http://localhost:8080/api/authenticate',
                {username: login, password: password},
                {headers: {'Content-Type': 'application/json'}}
            );
            if (response.data && response.data.token) {
                localStorage.setItem("jwtToken", response.data.token);
                console.log('Login successful', response.data);
                return response.data;
            }
        } catch (error) {
            console.error('Login failed:', error.response.data);
            throw new Error(error.response.data);
        }
    };


    const logout = async () => {
        try {
            const token = localStorage.getItem("jwtToken"); // Retrieve the token from local storage
            if (token) {
                await axiosInstance.post(
                    'http://localhost:8080/api/logout',
                    null,
                    {
                        headers: {
                            'Authorization': `Bearer ${token}` // Include the token in the Authorization header
                        },
                        withCredentials: true
                    }
                );
                localStorage.removeItem('jwtToken'); // Remove the token from local storage
                console.log("Logout successful");
            }
        } catch (error) {
            console.error('Failed to logout:', error.response.data);
        }
    };


    return {register, login, logout};
}
