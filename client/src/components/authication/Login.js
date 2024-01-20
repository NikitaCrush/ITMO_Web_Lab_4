import React, { useState } from "react";
import { Container, ButtonContainer, StyledButton, TextFieldStyle, Message } from "./loginStyles";
import { useAuth } from "../../suppliers/auth";
import {useDispatch} from "react-redux";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const auth = useAuth();
    const handleLoginChange = (e) => {
        const value = e.target.value;
        if (/^[a-zA-Z0-9.@]+$/.test(value) || value === "") {
            setUsername(value);
        }
    };
    const handlePasswordChange = (e) => {
        const value = e.target.value;
        if (/^[a-zA-Z0-9.@]+$/.test(value) || value === "") {
            setPassword(value);
        }
    };

    const dispatch = useDispatch();
    const handleLogin = async (e) => {
        e.preventDefault();
        if (!username || !password) {
            setMessage("You haven't filled in all the fields");
        } else {
            try {
                await auth.login(username, password);
                dispatch({ type: 'LOGIN' });
                setMessage("You have successfully logged in");
            } catch (error) {
                setMessage("Error logging in");
            }
        }
    };
    const handleRegister = async () => {
        if (!username || !password) {
            setMessage("You haven't filled in all the fields");
        } else {
            try {
                await auth.register(username, password);
                setMessage("You have successfully registered");
            } catch (error) {
                setMessage("The user is already registered");
            }
        }
    };
    return (
        <div>
            <form onSubmit={handleLogin}>
                <Container>
                    <TextFieldStyle
                        type="text"
                        value={username}
                        onChange={handleLoginChange}
                        label="Username"
                        variant="outlined"
                    />
                    <br/>
                    <TextFieldStyle
                        type="password"
                        value={password}
                        onChange={handlePasswordChange}
                        label="Password"
                        variant="outlined"
                    />
                    <br/>
                    <ButtonContainer>
                        <StyledButton type="submit">Login</StyledButton>
                        <StyledButton type="button" onClick={handleRegister}>Register</StyledButton>
                    </ButtonContainer>
                    {message && <Message>{message}</Message>}
                </Container>
            </form>
        </div>
    );
};
export default Login;