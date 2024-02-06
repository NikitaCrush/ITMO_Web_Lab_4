import {styled} from '@mui/system';
import {Button, TextField} from "@mui/material";

export const Container = styled('div')`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-left: auto;
    margin-right: auto;
    margin-top: 60px;
    width: 40%;

    /* Desktop */
    @media (min-width: 1255px) {
        width: 35%;
        margin-top: 60px;
    }
    /* Tablet */
    @media (min-width: 715px) and (max-width: 1255px) {
        width: 60%;
        margin-top: 80px;
    }
    /* Mobile */
    @media screen and (max-width: 715px) {
        width: 70%;
        margin-top: 40px;
    }
`;

export const TextFieldStyle = styled(TextField)`
    color: #212121;
    width: 100%;
    margin-bottom: 5px;
    font-family: Lato, Montserrat, sans-serif;
`;

export const ButtonContainer = styled(`div`)`
    display: flex;
    flex-direction: column;
    width: 100%;
    justify-content: center;
    align-items: center;
`;

export const StyledButton = styled(Button)`

    position: relative;
    width: 160px;
    height: 50px;
    line-height: 48px;
    background: #000;
    text-transform: uppercase;
    font-size: 25px;
    text-align: center;
    letter-spacing: 0.1em;
    text-decoration: none;
    transition: 0.5s;
    border: none;
    outline: none;
    cursor: pointer;
    overflow: hidden;
    color: white !important;

    &::before {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 50%;
        background: rgba(255, 255, 255, 0.1);
        z-index: 10;
    }

    &::after {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: linear-gradient(
                45deg,
                #c0392b,
                #f39c12,
                #f1c40f,
                #2ecc71,
                #3498db,
                #2980b9,
                #9b59b6,
                #8e44ad,
                #c0392b,
                #f39c12,
                #f1c40f,
                #2ecc71,
                #3498db,
                #2980b9,
                #9b59b6,
                #8e44ad
        );
        background-size: 400%;
        opacity: 0;
        filter: blur(20px);
        transition: 0.5s;
        animation: eff 20s ease infinite;
    }

    &:hover::before,
    &:hover::after {
        opacity: 1;
    }

    &:hover {
        color: rgba(255, 255, 255, 1);
    }

    &:hover {
        background: #0c0c0c;
    }


    /* Desktop */
    @media (min-width: 1255px) {
        font-size: 16px;
        margin-bottom: 10px;
    }
    /* Tablet */
    @media (min-width: 715px) and (max-width: 1254px) {
        font-size: 14px;
        margin-bottom: 7px;
    }
    /* Mobile */
    @media screen and (max-width: 715px) {
        font-size: 12px;
        margin-bottom: 5px;
    }


`;

export const Message = styled('div')`
    color: #212121;
    border: 1px solid #212121;
    text-align: center;
    margin-left: auto;
    margin-right: auto;
    width: 100%;
    border-radius: 6px;
    margin-top: 25px;
    padding: 10px;

    /* Desktop */
    @media (min-width: 1255px) {
        border-radius: 6px;
        margin-top: 15px;
        padding: 10px;
    }
    /* Tablet */
    @media (min-width: 715px) and (max-width: 1254px) {
        border-radius: 6px;
        margin-top: 40px;
        padding: 15px;
    }
    /* Mobile */
    @media screen and (max-width: 715px) {
        border-radius: 6px;
        margin-top: 50px;
        padding: 17px;
    }
`;