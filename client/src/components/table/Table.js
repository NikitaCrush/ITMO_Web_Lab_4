import React, {useEffect} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {getPoints} from '../../redux/actions/pointsActions';
import {StyledTableContainer, StyledTable, StyledTableRow, StyledTableCell } from './tableStyles';
import {Paper, TableBody, TableHead} from "@mui/material";

const PointsTable = () => {
    const dispatch = useDispatch();
    const points = useSelector((state) => state.points); // Ensure this is the correct path to your points in the state

    useEffect(() => {
        (async () => {
            try {
                await dispatch(getPoints());
            } catch (error) {
                console.error('Error loading points for the table', error);
            }
        })();
    }, [dispatch]);


    return (
        <Paper>
            <StyledTableContainer component={Paper}>
                <StyledTable>
                    <TableHead>
                        <StyledTableRow>
                            <StyledTableCell align="center">X</StyledTableCell>
                            <StyledTableCell align="center">Y</StyledTableCell>
                            <StyledTableCell align="center">R</StyledTableCell>
                            <StyledTableCell align="center">Result</StyledTableCell>
                            <StyledTableCell align="center">Time</StyledTableCell>
                        </StyledTableRow>
                    </TableHead>
                    <TableBody>
                        {points && points.map((point, index) => (
                            <StyledTableRow key={index}>
                                <StyledTableCell align="center">{point.x}</StyledTableCell>
                                <StyledTableCell align="center">{point.y}</StyledTableCell>
                                <StyledTableCell align="center">{point.r}</StyledTableCell>
                                <StyledTableCell align="center">{point.result ? "true" : "false"}</StyledTableCell>
                                <StyledTableCell align="center">{point.currentTime ? new Date(point.currentTime).toLocaleString() : ""}</StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </StyledTable>
            </StyledTableContainer>
            {/*<StyledButton variant="contained" onClick={() => reset()}>*/}
            {/*    Reset Form*/}
            {/*</StyledButton>*/}
        </Paper>
    );
};

export default PointsTable;