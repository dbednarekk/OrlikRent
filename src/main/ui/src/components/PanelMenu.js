import React from "react";
import { List, Box } from "@mui/material";
function PanelMenu(props) {
  return (
    <Box>
      <List component="nav" aria-label="panel menu">
        {props.children}
      </List>
    </Box>
  );
}

export default PanelMenu;
