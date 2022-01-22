import React from 'react';
import {Box, Grid,List, ListItem, ListItemIcon, ListItemText} from '@mui/material'
import Header from './Header';
import Footer from './Footer';
import PanelMenu from './PanelMenu';
import {Link, Route, Routes} from 'react-router-dom'
import PropTypes from 'prop-types';
function PanelLayout(props) {
  return (
      <Box>
      
           
            <Grid container >
                <Grid item  xs={2} md={3} xl={2}>
                    <PanelMenu color="red">
                        <List component="nav">
                            {
                                props.menu.map(({link, Icon, text}, index) => (
                                    <Link key={index} to={link}>
                                        <ListItem button>
                                            <ListItemIcon>
                                                <Icon style={{color: 'white'}}/>
                                            </ListItemIcon>
                                            <ListItemText>{text}</ListItemText>
                                        </ListItem>
                                    </Link>
                                ))
                            }
                        </List>
                    </PanelMenu>
                </Grid>

                <Grid item  xs={10} md={9} xl={10}>
                    {
                        props.menu.map(({link, text, Component}) => (
                            <Routes>
                            <Route exact path={link} element={  
                                <Component />}>
                             
                            </Route>
                            </Routes>
                        ))
                    }

                    {
                        props.otherRoutes && props.otherRoutes.map(({to, Component}) => (
                            
                            <Routes>
                                <Route key={to} exact path={to} element={ <Component />}>
                               
                            </Route>
                            </Routes>
                        ))
                    }
                </Grid>
           
            </Grid>
      </Box>
  );
}
PanelLayout.propTypes = {
    
};
export default PanelLayout;
