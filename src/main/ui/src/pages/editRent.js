 import { TextField } from '@mui/material';
import { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';

function EditRent() {
   
    const navigate = useNavigate();

    const currentAccount = JSON.parse(sessionStorage.getItem("id"));
    const [accountID, setAccountID] = useState('');
    const [pitchID, setPitchID] = useState('');
    const [start_date_rental, setStart_date_rental] = useState('');
    const [end_date_rental, setEnd_date_rental] = useState('');
    const [active, setActive] = useState('');
   
    const handleChangeActive = (event) => {
        setActive(
            event.target.checked,
          );
      };

    const handleEditRent = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            accountID,
            pitchID,
            start_date_rental,
            end_date_rental,
            active,
        });
        console.log(json);
        axios.put(`Rentals/updateRent/${currentAccount.id}`, json,{
            headers: {
                "Content-Type": "application/json",
            //     "Accept": "application/json",
            //     // "If-Match": currentAccount.etag,
            }
        })
    }

    const getRent = () => {
    return axios.get(`/Rentals/Rent/${currentAccount.id}`, )
    }

    useEffect( () => {
        getRent().then(res => {
            setAccountID(res.data.accountID)
            setPitchID(res.data.pitchID)
            setStart_date_rental(res.data.end_date_rental)
            setEnd_date_rental(res.data.end_date_rental)
            setActive(res.data.active)
        })
    }, [])

    return (
       
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
        <div className={ styles.body }>
            <h1>Edit rent</h1>
            <h3>AccountID:</h3>
            <TextField
                label={"AccountID *"}
                placeholder={accountID}
                value={accountID}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setAccountID(event.target.value)
                }}>
            </TextField>
            <h3>PitchID:</h3>
            <TextField
                label={"PitchID *"}
                placeholder={pitchID}
                value={pitchID}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setPitchID(event.target.value)
                }}>
            </TextField>
            <h3>Start date:</h3>
                <TextField
                    placeholder={start_date_rental}
                    value={start_date_rental}
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setStart_date_rental(event.target.value)
                    }}
                >
                </TextField>
            <h3>End date:</h3>
                <TextField
                    placeholder={end_date_rental}
                    value={end_date_rental}
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setEnd_date_rental(event.target.value)
                    }}
                >
                </TextField>
             <h3>Active:</h3>
                <FormControlLabel
                control={
                    <Switch checked={active} onChange={handleChangeActive} name="active" />
                }
                label="Active:"
                labelPlacement="start"
                style={{
                    marginTop: '16px'}}
            />
            <Button
                variant="success"
                style={{
                    width: '50%',
                    fontSize: '2rem',
                    padding: '10px 0',
                    marginTop: '16px',
                }}
                onClick={handleEditRent}
                >{"Edit Rent"}</Button>
            </div>
        </div>
    )
}
    
export default EditRent