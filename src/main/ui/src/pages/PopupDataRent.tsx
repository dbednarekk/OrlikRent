import React from "react";
import Button from '@material-ui/core/Button';
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import { useState, useEffect } from "react";
import axios from "../Services/URL";
import styletb from '../styles/tableStyle.module.css'


export interface PopupDataRent {
    open: boolean,
    onCancel: () => void,
    id: string,
}

export default function PopupDataRent({open, onCancel, id}){

    const [id1, setId1] = useState('');
    const [accountID, setAccountID] = useState('');
    const [pitchID, setPitchID] = useState('');
    const [startDate, setStartDate] = useState('');
    const [endDate, setEndDate] = useState('');
    const [active, setActive] = useState(false);
    const token = sessionStorage.getItem("JWTToken")
 
    const handleOpen = () => {
            return axios.get(`/Rentals/Rent/${id}`,{
                headers:{
                    'Authorization': `Bearer ${token}`
                }
            } )
    }

    useEffect( () => {
        handleOpen().then(res => {
            setId1(res.data.id)
            setAccountID(res.data.accountID)
            setPitchID(res.data.pitchID)
            setStartDate(res.data.start_date_rental)
            setEndDate(res.data.end_date_rental)
            setActive(res.data.active)
        })
    }, [])

    return(
        <Dialog

            maxWidth={false}
            open={open}
            onClose={onCancel}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description">
            <DialogTitle id="alert-dialog-title"></DialogTitle>
            <DialogContent>
            <Grid item style={{display: "block" }} className={styletb['change-item']}>
                    <tr>
                        <td className={styletb.td}><h2>{"DI"}</h2></td>
                        <td className={styletb.td}><h2>{"AccountID"}</h2></td>
                        <td className={styletb.td}><h2>{"PitchID"}</h2></td>
                        <td className={styletb.td}><h2>{"Start Date"}</h2></td>
                        <td className={styletb.td}><h2>{"End Date"}</h2></td>
                        <td className={styletb.td}><h2>{"Active"}</h2></td>
                    </tr>
                    <tr>
                        <td className={styletb.tdData}><h2>{id1}</h2></td>
                        <td className={styletb.tdData}><h2>{accountID}</h2></td>
                        <td className={styletb.tdData}><h2>{pitchID}</h2></td>
                        <td className={styletb.tdData}><h2>{startDate}</h2></td>
                        <td className={styletb.tdData}><h2>{endDate}</h2></td>
                        <td className={styletb.tdData}><h2>{'' + active}</h2></td>
                    </tr>
                </Grid>
            </DialogContent>
            <DialogActions>
                <Button onClick={onCancel} color="primary" autoFocus>
                    {'cancel'}
                </Button>
            </DialogActions>
        </Dialog>

    );
}
