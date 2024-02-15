const initialState = {
    x: null,
    y: null,
    r: '0',
    points: [],
    authStatus: false,
};
const pointReducer = (state = initialState, action) => {
    switch (action.type) {
        case 'SET_X':
            return {...state, x: action.payload};
        case 'SET_Y':
            return {...state, y: action.payload};
        case 'SET_R':
            return {...state, r: action.payload};
        case 'ADD_POINTS':
            return {
                ...state,
                points: [...state.points, action.payload]
            };
        case 'GET_POINTS_SUCCESS':
            return {
                ...state,
                points: action.payload
            };
        case 'RESET_ALL_POINTS':
            return {
                ...state,
                points: []
            };
        case 'GET_RESULT':
            return {
                ...state,
                points: action.payload
            };
        case 'LOGOUT':
            return {initialState};
        case 'LOGIN':
            return {...state, authStatus: true};
        case 'DEFAULT_STATE':
            return {initialState};
        default:
            return state;
    }
};
export default pointReducer;