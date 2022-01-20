import React from 'react';
import { List, ListItem, ListItemIcon, Box  } from '@mui/material';
import {Link} from 'react-router-dom';
function PanelMenu(props) {
  return( 
    <Box>
         <List component="nav" aria-label="panel menu">
                {props.children}
            </List>
    </Box>
  );
}

export default PanelMenu;
