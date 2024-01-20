import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import LoginPage from '../authication/Login';
import MainPage from '../mainPage/MainPage';
import Intro from '../commandIntro/Intro';
import './App.css';

function App() {
    const authStatus = useSelector((state) => state.authStatus);
    return (
        <Router>
            <Routes>
                <Route
                    path="/"
                    element={
                        <>
                            <Intro />
                            {authStatus ? <Navigate to="/main" /> : <LoginPage />}
                        </>
                    }
                />
                <Route path="/main" element={authStatus ? <MainPage /> : <Navigate to="/" />} />
            </Routes>
        </Router>
    );
}

export default App;