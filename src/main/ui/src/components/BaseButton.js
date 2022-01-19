import React from "react";
import Button from '@mui/material/Button'
import Box from '@mui/material/Box'

function BaseButton(props) {
  return (
    <Box>
      <Button variant="contained" onClick={props.onClick} disabled={props.enable}>
        {props.name}
      </Button>
    </Box>
  );
}

export default BaseButton;
