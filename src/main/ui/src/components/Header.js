import React from "react";
import logo from "../images/logoOrlik.png";
import BaseButton from "./BaseButton";
import styles from "../styles/Header.module.css";
import Box from "@mui/material/Box";
import { Link } from "react-router-dom";
import PositionedMenu from "./PositionedMenu";
import RefreshIcon from '@mui/icons-material/Refresh';
import IconButton from '@mui/material/IconButton';
function Header(props) {
  const isLogged = sessionStorage.getItem("JWTToken") === null ? false : true;
  const login = JSON.parse(sessionStorage.getItem("Login"))
  const auth = JSON.parse(sessionStorage.getItem("Auth"))

  return (
    <Box className={styles.header}>
      
      <Link to="/">
        <img src={logo} alt="logo" className={styles.logo} />
      </Link>
      
      <h1 className={styles.headerh1}> {props.title}</h1>
      <Box className={styles.headerButton}>
     
        {isLogged ? (
          <>
           <h4 className={styles.headerh4}>{login}</h4>
           <h4 className={styles.headerh4} >{auth}</h4>
           <PositionedMenu/>
          </>
        ) : (
          <>
          
            <Link to="/login">
              <BaseButton name="Zaloguj się" />
            </Link>
            <Link to="/register">
            <BaseButton name="Zarejestruj się" />
            </Link>
            
          </>
        )}
         <IconButton style={{ backgroundColor: 'lightgrey', marginLeft: '10px' }}  onClick={() => {window.location.reload(true)}}>
                    <RefreshIcon/>
                </IconButton>
      </Box>
    </Box>
  );
}

export default Header;
