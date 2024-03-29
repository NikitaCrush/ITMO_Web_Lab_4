import {useDispatch, useSelector} from "react-redux";
import {
    setX,
    setY,
    setR,
    sendPoints,
    resetPoints,
    getPoints,
    logoutAuth,
} from "../../redux/actions/pointsActions";
import {
    StyledFormControl,
    StyledFormLabel,
    StyledRadioGroup,
    StyledFormControlLabel,
    StyledRadio,
    Container,
    StyledTextField,
    StyledButton,
    Message,
    ButtonContainer,
} from "./inputsStyles";
import React, {useEffect, useState} from "react";
import {useAuth} from "../../suppliers/auth";
import {useNavigate} from "react-router-dom";

const Inputs = () => {
    let result;
    const x = useSelector((state) => state.x);
    const y = useSelector((state) => state.y);
    const r = useSelector((state) => state.r);
    const points = useSelector((state) => state.points);
    const [message, setMessage] = useState("");
    const [isRadiusSelected, setIsRadiusSelected] = useState(false);
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const auth = useAuth();

    useEffect(() => {
        updateSVG();
    }, [points]);

    const handleXChange = (event) => {
        if (event.target.value === "") {
            setMessage("You didn't choose X");
        } else {
            setMessage("");
            dispatch(setX(event.target.value));
        }
    };
    const handleYChange = (event) => {
        const Y = event.target.value;
        const regex = /^-?\d*\.?\d*$/;
        if (!regex.test(Y) || Y < -5 || Y > 3 || Y === "") {
            setMessage("Y must be a number in the range from -5 to 3");
            dispatch(setY(null));
        } else {
            setMessage("");
            dispatch(setY(Y));
        }
    };
    const handleRChange = async (event) => {
        if (event.target.value === "") {
            setMessage("You didn't choose R");
        } else {
            setMessage("");
            changeR(event.target.value);
            setIsRadiusSelected(true);
            dispatch(setR(event.target.value));
            await dispatch(getPoints(parseFloat(event.target.value)));
        }
    };
    const handleSubmit = async () => {
        try {
            if (!x || !y || !r) {
                setMessage("Fill in all the fields before sending");
                return;
            }
            setMessage("");
            dispatch(sendPoints(x, y, r));
            await dispatch(getPoints(r));
        } catch (error) {
            console.error("Error:", error);
        }
    };

    const updateSVG = () => {
        clearSVG();
        if (points && Array.isArray(points)) {
            points.forEach((point) => {
                const {x, y, r, result} = point;
                calculator(x, y, r, result);
            });
        }
    };

    const handleLogout = async () => {
        clearSVG();
        dispatch(logoutAuth());
        await auth.logout();
        navigate("/");
    };
    const handleDelete = async () => {
        clearSVG();
        await dispatch(resetPoints());
        setMessage("The points have been deleted");
        setTimeout(() => {
            setMessage("");
        }, 3000);
    };

    const calculator = (x, y, r, result) => {
        const width = 400;
        const height = 400;
        const centerX = width / 2;
        const centerY = height / 2;
        const cx = centerX + x * (width / (3.3 * r));
        const cy = centerY - y * (height / (3.3 * r));
        setRound(cx, cy, result);
    }


    useEffect(() => {
        const svg = document.querySelector("svg");
        const getXY = (svg, event) => {
            const rect = svg.getBoundingClientRect();
            return {x: event.clientX - rect.left, y: event.clientY - rect.top};
        };
        const drawPoint = async (event) => {
            if (!isRadiusSelected) {
                setMessage("You have not chosen a radius");
                return;
            }
            let radius = r;
            const point = getXY(svg, event);
            const tempX = point.x - 200;
            const tempY = 200 - point.y;
            const temp = 120 / radius;
            const newX = (tempX / temp).toFixed(1);
            const newY = (tempY / temp).toFixed(1);
            try {
                const action = sendPoints(newX, newY, radius);
                const result = await action(dispatch);
                console.log("RESULT:", result);
                dispatch(setX(newX));
                dispatch(setY(newY));
                calculator(newX, newY, radius, result);
            } catch (error) {
                console.error("Error sending points:", error);
            }
            // const result = sendPoints(newX, newY, radius).getResult();
            // console.log("RESULT BLYAT ", result);
            // dispatch(setX(newX));
            // dispatch(setY(newY));
            // dispatch(sendPoints(newX, newY, radius));
            // calculator(newX, newY, radius, result);
        };

        svg.addEventListener("click", drawPoint);

        return () => {
            svg.removeEventListener("click", drawPoint);
        };
    }, [updateSVG, calculator, dispatch, isRadiusSelected, r]);

    const setRound = async (cx, cy, result) => {
        const svg = document.querySelector("svg");
        const circle = document.createElementNS(
            "http://www.w3.org/2000/svg",
            "circle"
        );
        circle.setAttribute("cx", cx);
        circle.setAttribute("cy", cy);
        circle.setAttribute("r", "5");
        if (result) {
            circle.setAttribute("fill", "green");
        } else {
            circle.setAttribute("fill", "red");
        }
        svg.appendChild(circle);
    };

    function clearSVG() {
        const svg = document.querySelector("svg");
        const circles = svg.querySelectorAll("circle");
        circles.forEach((circle) => {
            svg.removeChild(circle);
        });
    }

    function changeR(r) {
        const elements = {
            Ry: r,
            "R/2y": r / 2,
            "-R/2y": -r / 2,
            "-Ry": -r,
            Rx: -r,
            "R/2x": -r / 2,
            "-R/2x": r / 2,
            "-Rx": r,
        };
        for (const id in elements) {
            const element = document.getElementById(id);
            if (element) {
                element.textContent = elements[id] ? elements[id].toString() : "";
            }
        }
    }


    return (
        <Container>
            <StyledFormControl>
                <StyledFormLabel component="legend">X: </StyledFormLabel>
                <StyledRadioGroup
                    aria-label="x"
                    name="x"
                    value={x}
                    onChange={handleXChange}
                >
                    {["-2", "-1.5", "-1", "-0.5", "0", "0.5", "1", "1.5", "2"].map(
                        (value) => (
                            <StyledFormControlLabel
                                key={value}
                                value={value}
                                control={<StyledRadio/>}
                                label={value}
                            />
                        )
                    )}
                </StyledRadioGroup>
            </StyledFormControl>

            <StyledFormControl>
                <StyledFormLabel component="legend">Y: </StyledFormLabel>
                <StyledTextField
                    type="number"
                    placeholder={"-5..3"}
                    onChange={handleYChange}
                    value={y !== null ? y : ""}/>
            </StyledFormControl>

            <StyledFormControl>
                <StyledFormLabel component="legend">R: </StyledFormLabel>
                <StyledRadioGroup
                    row
                    aria-label="r"
                    name="r"
                    onChange={handleRChange}
                    value={r}
                >
                    {["1", "1.5", "2", "2.5", "3", "3.5", "4"].map((value) => (
                        <StyledFormControlLabel
                            key={value}
                            value={value}
                            control={<StyledRadio/>}
                            label={value}
                        />
                    ))}
                </StyledRadioGroup>
            </StyledFormControl>
            <ButtonContainer>
                <StyledButton onClick={handleSubmit}>Send</StyledButton>
                <StyledButton onClick={handleDelete}>Clear plane</StyledButton>
                <StyledButton onClick={handleLogout}>Logout</StyledButton>
            </ButtonContainer>
            {message && <Message>{message}</Message>}
        </Container>
    );
};
export default Inputs;