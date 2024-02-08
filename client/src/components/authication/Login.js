import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useAuth } from "../../suppliers/auth";
import { Container, ButtonContainer, StyledButton, TextFieldStyle, Message } from "./loginStyles";

const Login = () => {
    const [credentials, setCredentials] = useState({ username: "", password: "" });
    const [message, setMessage] = useState("");
    const { login, register } = useAuth();
    const dispatch = useDispatch();

    const handleChange = ({ target: { name, value } }) => {
        if (/^[a-zA-Z0-9.@]*$/.test(value)) {
            setCredentials(prev => ({ ...prev, [name]: value }));
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const { username, password } = credentials;
        if (!username || !password) {
            setMessage("You haven't filled in all the fields");
            return;
        }

        try {
            await login(username, password);
            dispatch({ type: 'LOGIN' });
            setMessage("You have successfully logged in");
        } catch (error) {
            setMessage("Error logging in");
        }
    };

    const handleRegister = async () => {
        const { username, password } = credentials;
        if (!username || !password) {
            setMessage("You haven't filled in all the fields");
            return;
        }

        try {
            await register(username, password);
            setMessage("You have successfully registered");
        } catch (error) {
            setMessage("The user is already registered");
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <Container>
                <TextFieldStyle
                    name="username"
                    type="text"
                    value={credentials.username}
                    onChange={handleChange}
                    label="Username"
                    variant="outlined"
                />
                <TextFieldStyle
                    name="password"
                    type="password"
                    value={credentials.password}
                    onChange={handleChange}
                    label="Password"
                    variant="outlined"
                />
                <ButtonContainer>
                    <StyledButton type="submit">Login</StyledButton>
                    <StyledButton type="button" onClick={handleRegister}>Register</StyledButton>
                </ButtonContainer>
                {message && <Message>{message}</Message>}
            </Container>
        </form>
    );
};

export default Login;
