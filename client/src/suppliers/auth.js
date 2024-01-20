import axios from 'axios';


export function useAuth() {
    const register = async (login, password) => {
        try {
            const response = await axios.post(
                'http://localhost:8080/api/register',
                { username: login, password: password },
                { headers: { 'Content-Type': 'application/json' }}
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
            const response = await axios.post(
                'http://localhost:8080/api/authenticate',
                { username: login, password: password },
                { headers: { 'Content-Type': 'application/json' }}
            );
            if (response.data && response.data.token) {
                // Store the JWT token in local storage or context
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
            await axios.post(
                'http://localhost:8080/api/logout',
                null,
                { withCredentials: true }
            );
            localStorage.removeItem('jwtToken'); // Remove the JWT token from local storage
            console.log("Logout successful");
        } catch (error) {
            console.error('Failed to logout:', error.response.data);
        }
    };


    return { register, login, logout };
}
