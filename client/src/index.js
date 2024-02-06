import {createRoot} from 'react-dom/client';
import React from 'react';
import {Provider} from 'react-redux';
import store from './redux/store';
import './index.css';
import App from './components/authPage/App';


const container = document.getElementById('root');
const root = createRoot(container);
root.render(
    <React.StrictMode>
        <Provider store={store}>
            <App/>
        </Provider>
    </React.StrictMode>
);