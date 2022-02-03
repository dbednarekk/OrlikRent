import React from "react";
import Button from '@material-ui/core/Button';
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import { useState, useEffect } from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';
import styletb from '../styles/tableStyle.module.css'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import useErrorHandler from "../errorHandler.ts";
import {useSnackbarQueue} from "../components/Snackbar.ts"
export interface PopupRentPitch {
    open: boolean,
    onCancel: () => void,
    id: string,
    pitch: string,
  
}

export default function PopupRentPitch({open, onCancel, id, pitch}){
    const [pitchName, setPitchName] = useState(pitch)
    const [start_date_rental, setStart_date_rental] = useState(new Date());
    const [end_date_rental, setEnd_date_rental] = useState(new Date());
    const token = sessionStorage.getItem('JWTToken') ;
    const login = JSON.parse(sessionStorage.getItem('Login')) ;
    const handleError = useErrorHandler()
    const showSuccess = useSnackbarQueue('success')
    const [idd,setID] = useState('')
    useEffect(() =>{
        axios.get(`Account/login/${login}`,{
            headers:{
              'Authorization': `Bearer ${token}`
            }}).then((res) => {
                setID(res.data.id)
              }).catch(error =>{
                const message = error.response.data
                handleError(message, error.response.status)
              })
               
    },[])
    const handleSetRent = () => {
      
           
        const json = JSON.stringify({
          accountID: idd, 
          pitchID: id,
          start_date_rental: start_date_rental.toISOString(),
          end_date_rental: end_date_rental.toISOString(),
          active: true,
        });
        console.log(json);
         axios.post("/Rentals/addRent/", json, {
          headers: {
            "Content-Type": "application/json",
            'Authorization': `Bearer ${token}`
          }
        }).then((res) =>  {
              showSuccess("successful action")
              onCancel()
          }).catch(error =>{
            console.log(error.response.data)
            const message = error.response.data
            handleError(message, error.response.status)
          });;
      };

    return(
        <Dialog
            fullWidth={true}
            maxWidth={"sm"}
            open={open}
            onClose={onCancel}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description">
            <DialogTitle id="alert-dialog-title"></DialogTitle>
            <DialogContent>
            <Grid item style={{display: "block" }} className={styletb['change-item']}>
                   <div>
                <DatePicker
                    selected={start_date_rental}
                    onChange={date => setStart_date_rental(date)}
                    showTimeSelect
                    timeFormat="HH:mm:ss"
                    timeIntervals={15}
                    timeCaption="time"
                    dateFormat="MMM d, yyyy h:mm aa"
                />
                <DatePicker
                    selected={end_date_rental}
                    onChange={date => setEnd_date_rental(date)}
                    showTimeSelect
                    timeFormat="HH:mm"
                    timeIntervals={15}
                    timeCaption="time"
                    dateFormat="MMM d, yyyy h:mm aa"
                />
                </div>
                <div style={{height
                    : '200px'}}></div>
                </Grid>
            </DialogContent>
            <DialogActions>
                <Button onClick={handleSetRent} color="primary" autoFocus>
                    {'Rent ' + pitch.name}
                </Button>
                <Button onClick={onCancel} color="primary" autoFocus>
                    {'cancel'}
                </Button>
            </DialogActions>
        </Dialog>

    );
}