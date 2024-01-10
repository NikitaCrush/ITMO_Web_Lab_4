import { styled } from '@mui/system';
import { Button, TextField } from "@mui/material";

export const Container = styled('div')`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: auto;
  margin-right: auto;
  margin-top: 60px;
  width: 40%;

    /* Desctop */
  @media (min-width: 1255px) {
    width: 35%;
    margin-top: 60px;
  }
    /* Tablet */
  @media (min-width: 715px) and (max-width: 1254px) {
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

  & .MuiOutlinedInput-root {
    & fieldset {
      border-color: #212121;
    }
    &:hover fieldset {
      border-color: #212121;
    }
    &.Mui-focused fieldset {
      border-color: #212121;
    }
    & input {
      color: #212121;
      background-color: white;
    }
    & .MuiInputLabel-root {
      color: #212121 !important;
    }
  }
`;

export const ButtonContainer = styled(`div`)`
  display: flex;
  flex-direction: column;
  width: 100%;

`;

export const StyledButton = styled(Button)`
  font-family: Lato, Montserrat, sans-serif;
  color: white;
  background-color: #212121;
  font-weight: bold;
  width: 100%;
  font-size: 16px;
  margin-bottom: 10px;

    /* Desctop */
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

  &:hover {
    background-color: white;
    color: #212121;
  }

  &:active {
    background-color: white;
    color: #212121;
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
    
    /* Desctop */
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