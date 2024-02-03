import { styled } from '@mui/material/styles';
import {FormControl, FormLabel, RadioGroup, FormControlLabel, Radio, TextField, Button} from '@mui/material';

export const Container = styled('div')`
  font-family: Lato, Montserrat, sans-serif;
  display: flex;
  flex-direction: column;
  margin-top: 10px;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  /* Desktop */
  @media (min-width: 1255px) {
    margin-top: 7px;
  }
  /* Tablet */
  @media (min-width: 715px) and (max-width: 1255px) {
    margin-top: 10px;
  }
  /* Mobile */
  @media screen and (max-width: 715px) {
    margin-top: 10px;
  }
`;

export const StyledFormControl = styled(FormControl)`
  font-size: 14px;
  color: #212121;
  display: grid;
  grid-template-columns: auto 1fr;
  grid-gap: 8px;
  align-items: center;
  margin-bottom: 16px;
  transition: color 0.3s ease;
  &:hover {
    color: #212121;
  }
  /* Desktop */
  @media (min-width: 1255px) {
    margin-bottom: 5px;
  }
  /* Tablet */
  @media (min-width: 715px) and (max-width: 1255px) {
    margin-bottom: 2px;
  }
  /* Mobile */
  @media screen and (max-width: 715px) {
    margin-bottom: 2px;
  }
`;

export const StyledFormLabel = styled(FormLabel)`
  font-size: 14px;
  color: #212121;
  &:hover {
    color: #212121; 
  }
`;

export const StyledRadioGroup = styled(RadioGroup)`
  font-size: 14px;
  color: #212121;
  display: flex;
  flex-direction: row;
`;

export const StyledFormControlLabel = styled(FormControlLabel)`
  font-size: 14px;
  color: #212121;
  & .MuiRadio-root.Mui-checked {
    color: white;
  }
  &:hover {
    border-color: white; 
  }
  span {
    font-size: 14px;
  }
`;

export const StyledRadio = styled(Radio)`
  color: #212121;
  & .MuiSvgIcon-root {
    font-size: 14px;
  }
  &.Mui-checked {
    color: white;
    & ~ .MuiFormLabel-root {
      color: white; 
    }
  }
`;

export const StyledTextField = styled(TextField)`
  background-color: white;
  color: #212121;
  font-size: 14px;
  width: 150px;
  border-color: #212121;
  border-radius: 6px;
  & .MuiOutlinedInput-root {
    & fieldset {
      border-color: #212121;
    }
    .MuiInputLabel-root {
      font-size: 12px; 
    }

    &:hover fieldset {
      border-color: #212121;
    }
    .MuiInputBase-input {
      font-size: 12px; 
    }
    &.Mui-focused fieldset {
      border-color: #212121;
    }
`;
export const ButtonContainer = styled(`div`)`
  display: flex;
  justify-content: space-around;
    align-items: center;
  width: 100%;
  gap: 5px;
`;
export const StyledButton = styled(Button)`

    position: relative;
    width: 220px;
    height: 50px;
    border: none;
    outline: none;
    color: #fff;
    background: #111;
    cursor: pointer;
    z-index: 0;
    border-radius: 10px;
    overflow: hidden;

    &:before {
        content: '';
        background: linear-gradient(45deg, #f545d7, #7f52fa, #2e4dff, #14a102, #00ffd5, #002bff, #7a00ff, #ff00c8, #ff0000);
        position: absolute;
        top: -2px;
        left: -2px;
        background-size: 400%;
        z-index: -1;
        filter: blur(5px);
        width: calc(100% + 4px);
        height: calc(100% + 4px);
        animation: glowing 20s linear infinite;
        opacity: 0;
        transition: opacity 0.3s ease-in-out;
        border-radius: 10px;
    }

    &:hover:before {
        opacity: 1;
    }

    &:after {
        content: '';
        position: absolute;
        width: 100%;
        height: 100%;
        //background: #111;
        left: 0;
        top: 0;
        border-radius: 10px;
    }

    &:active {
        color: #fff;
    }

    &:active:after {
        background: transparent;
    }

    @keyframes glowing {
        0% { background-position: 0 0; }
        50% { background-position: 400% 0; }
        100% { background-position: 0 0; }
    }
    
    //display: flex;
  //justify-content: space-between;
  //font-family: Lato, Montserrat, sans-serif;
  //color: #212121;
  //text-align: center;
  //margin-left: auto;
  //margin-right: auto;
  //background-color: white;
  //font-weight: bold;
  //width: auto; 
  //max-width: 80%;
  //font-size: 12px;
  //margin-bottom: 5px;
  
  /* Desktop */
  @media (min-width: 1255px) {
    font-size: 14px;
    margin-bottom: 10px;
  }
  /* Tablet */
  @media (min-width: 715px) and (max-width: 1255px) {
    font-size: 12px;
    margin-bottom: 7px;
  }
  /* Mobile */
  @media screen and (max-width: 715px) {
    font-size: 12px;
    margin-bottom: 5px;
  }

  //&:hover {
  //  background-color: #212121;
  //  color: white;
  //}
  //
  //&:active {
  //  background-color: #212121;
  //  color: white;
  //}
`;
export const Message = styled('div')`
  color: #212121;
  border: 1px solid #212121;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  width: 90%;
  border-radius: 6px;
  margin-top: 10px;
  padding: 10px;
  
  /* Desktop */
  @media (min-width: 1255px) {
    margin-top: 10px;
    font-size: 12px;
  }
  /* Tablet */
  @media (min-width: 715px) and (max-width: 1255px) {
    margin-top: 5px;
    font-size: 12px;
  }
  /* Mobile */
  @media screen and (max-width: 715px) {
    margin-top: 5px;
    font-size: 12px;
  }
`;