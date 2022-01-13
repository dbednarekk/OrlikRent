import React from "react";
import logo from "../images/logoOrlik.png";
import BaseButton from "./BaseButton";
import styles from "../styles/Header.module.css"
import Box from "@mui/material/Box"
import { style } from "@mui/system";
function Header() {
  return (
    <Box className={styles.header}>
      <img src={logo} alt="logo" className={styles.logo} />
      <h1> Witaj w wypożyczalni boisk!</h1>
      <Box className={styles.headerButton}>
      <BaseButton name="Zaloguj się" />
      <BaseButton name="Zarejestruj się" />
      </Box>
    </Box>
  );
}

export default Header;
