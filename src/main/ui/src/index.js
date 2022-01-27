import React, {createRef} from "react";
import ReactDOM from "react-dom";
import "./styles/index.css";
import App from "./App";
import { SnackbarProvider } from "notistack";
import Slide from '@mui/material/Slide';
import Button from "@material-ui/core/Button";



const Root =()=>{
    const notistackRef = React.createRef();

    const handleDismiss = (key) => () => {
        notistackRef.current?.closeSnackbar(key)
    }
    return(
        <React.StrictMode>
            <SnackbarProvider
                ref={notistackRef}
                anchorOrigin={{
                    vertical: "bottom",
                    horizontal: "left",
                }}
                action={key => (
                    <Button onClick={handleDismiss(key)} style={{color: 'white'}}>
                        dismiss
                    </Button>
                )}
                TransitionComponent={Slide}
            >
                <App />
            </SnackbarProvider>
        </React.StrictMode>);
}
ReactDOM.render(
    <Root/>,
  document.getElementById("root")
);
