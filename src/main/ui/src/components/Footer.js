import React from "react";
import Box from "@mui/material/Box";
import styles from "../styles/Footer.module.css";
function Footer() {
  return (
    <Box className={styles.footer}>
      <h2>Damian Bednarek  &  Michał Kłyż</h2>
      <h3>@ 2022</h3>
    </Box>
  );
}

export default Footer;
