import React from "react";
import CheckIcon from '@mui/icons-material/Check';
import CloseIcon from '@mui/icons-material/Close';
const ActiveIcon = (
    {active}) =>
    active ? <CheckIcon style={{fill: '#17C3B2'}} /> : <CloseIcon style={{fill: '#C63A48'}} />

export default ActiveIcon;
