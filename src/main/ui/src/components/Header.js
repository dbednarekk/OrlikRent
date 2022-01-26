import React from "react";
import logo from "../images/logoOrlik.png";
import BaseButton from "./BaseButton";
import styles from "../styles/Header.module.css";
import Box from "@mui/material/Box";
import { Link } from "react-router-dom";

function Header(props) {
  const isLogged = sessionStorage.getItem("JWTToken") === null ? false : true;
  const login = sessionStorage.getItem("Login")
  const auth = sessionStorage.getItem("Auth")
  const handleLogOut =() =>{
    sessionStorage.clear()
    document.location.replace('/OrlikRentPAS/')
  }
  return (
    <Box className={styles.header}>
      <Link to="/">
        <img src={logo} alt="logo" className={styles.logo} />
      </Link>
      <h1 className={styles.headerh1}> {props.title}</h1>
      <Box className={styles.headerButton}>
        {isLogged ? (
          <>
           <h4>{login}</h4>
           <h4>{auth}</h4>
           <BaseButton name="Wyloguj się" onClick={handleLogOut}/>
          </>
        ) : (
          <>
            <Link to="/login">
              <BaseButton name="Zaloguj się" />
            </Link>
            <BaseButton name="Zarejestruj się" />
          </>
        )}
      </Box>
    </Box>
  );
}

export default Header;
