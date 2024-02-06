import axiosInstance from "../../axiosInstance";

export const setX = (x) => ({type: 'SET_X', payload: x});
export const setY = (y) => ({type: 'SET_Y', payload: y});
export const setR = (r) => ({type: 'SET_R', payload: r});
export const logoutAuth = () => ({type: 'LOGOUT'})
export const sendPoints = (x, y, r) => {
    return async function (dispatch) {
        try {
            const token = localStorage.getItem("jwtToken");
            const currentTime = new Date().toISOString();
            const response = await axiosInstance.post(
                `http://localhost:8080/api/points`,
                JSON.stringify({x, y, r, currentTime}),
                {
                    withCredentials: true,
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`,
                    }
                }
            );
            if (response.status === 200) {
                dispatch({
                    type: 'ADD_POINTS',
                    payload: response.data
                });
                console.log(`Point successfully saved:`, response.data);
            }
        } catch (error) {
            console.error('Error sending point:', error);
        }
    };
};

export const getPoints = () => {
    return async (dispatch) => {
        try {
            const token = localStorage.getItem("jwtToken");
            const response = await axiosInstance.get(
                `http://localhost:8080/api/points`,
                {
                    headers: {
                        'Authorization': `Bearer ${token}`,
                        'Content-Type': 'application/json'
                    },
                    withCredentials: true
                }
            );

            dispatch({
                type: 'GET_POINTS_SUCCESS',
                payload: response.data
            });

            console.log('Points successfully retrieved:', response.data);
        } catch (error) {
            console.error("Error getting points", error);
        }
    };
};


export const resetPoints = () => {
    return async function (dispatch) {
        try {
            const token = localStorage.getItem("jwtToken");
            const response = await axiosInstance.post(
                `http://localhost:8080/api/points/clear`,
                null,
                {
                    withCredentials: true,
                    headers: {
                        'Authorization': `Bearer ${token}`,
                    }
                }
            );
            if (response.status === 200) {
                dispatch({type: 'RESET_ALL_POINTS'});
                console.log('Points cleared successfully');
            }
        } catch (error) {
            console.error('Error clearing points:', error);
        }
    };
};

