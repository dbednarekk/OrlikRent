import React from "react";
import ReactDOM from "react-dom";
import "./styles/index.css";
import App from "./App";
import { SnackbarProvider } from "notistack";
import Slide from '@mui/material/Slide';
ReactDOM.render(
  <React.StrictMode>
    <SnackbarProvider
      anchorOrigin={{
        vertical: "bottom",
        horizontal: "left",
      }}
      TransitionComponent={Slide}
    >
      <App />
    </SnackbarProvider>
  </React.StrictMode>,
  document.getElementById("root")
);
