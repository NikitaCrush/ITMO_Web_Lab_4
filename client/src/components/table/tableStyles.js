import { styled } from '@mui/system';
import {TableCell, TableContainer, Table, TableRow, Paper} from '@mui/material';

export const StyledTableContainer = styled(TableContainer)`
  max-height: 800px;
  width: 45%;
  margin-top: 30px;
  margin-right: 50px;
  padding: 5px;
  border-radius: 10px;
  background-color: #333333;
  overflow-y: auto;

  /* Desktop */
  @media (min-width: 1255px) {
    float: right;
    margin-top: 20px;
    width: 45%;
    max-height: 600px;
  }
  /* Tablet */
  @media (min-width: 715px) and (max-width: 1255px) {
    margin-top: 5px;
    margin-right: auto;
    margin-left: auto;
    width: 95%;
    height: 45%;
    max-height: 380px;
    overflow-y: auto;
  }
  /* Mobile */
  @media screen and (max-width: 715px) {
    margin-top: 5px;
    margin-right: auto;
    margin-left: auto;
    width: 98%;
    height: 45%;
    max-height: 200px;
    overflow-y: auto;

  }
`;
export const StyledTable = styled(Table)`
  color: #212121 solid;
`;

export const StyledTableRow = styled(TableRow)`
  background-color: #4d4d4d;
  color: #cccccc;
`;

export const StyledTableCell = styled(TableCell)`
  background-color: #4d4d4d;
  color: #cccccc;
`;

export const StyledPaper = styled(Paper)`
    root{
        backgroundColor: '#4d4d4d !important',
    },
`;
