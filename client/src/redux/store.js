import { configureStore } from '@reduxjs/toolkit';
import {thunk} from 'redux-thunk';
import pointReducer from "./reducer/pointsReducer";

const loadState = () => {
    try {
        const serializedState = localStorage.getItem("reduxState");
        if (serializedState === null) {
            return undefined;
        }
        return JSON.parse(serializedState);
    } catch (err) {
        return undefined;
    }
};

const store = configureStore({
    reducer: pointReducer,
    preloadedState: loadState(),
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(thunk),
});

store.subscribe(() => {
    try {
        const stateToSave = store.getState();
        localStorage.setItem("reduxState", JSON.stringify(stateToSave));
    } catch (err) {
        console.error("Error saving state in localStorage:", err);
    }
});

export default store;