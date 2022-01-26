 import { TextField } from '@mui/material';
import { useState } from "react";
import { useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import Select from 'react-select';

import useErrorHandler from "../errorHandler";
import {useSnackbarQueue} from "../components/Snackbar"
import axios from "../Services/URL";

function AddBPitch() {
   
    const navigate = useNavigate();


    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [lights, setLights] = useState(false);
    const [sector, setSector] = useState('');
    const [minP, setMinP] = useState('');
    const [maxP, setMaxP] = useState('');
    const handleError = useErrorHandler()
    const showSuccess = useSnackbarQueue('success')
    const [numberOfBaskets, setNumberOfBaskets] = useState('');
    const token = sessionStorage.getItem("JWTToken")
    const options1 = [
        { value: 'FULL_SIZE', label: 'Full' },
        { value: 'HALF_SIZE', label: 'Half' },
        ]

    const handleChangeLights = (event) => {
        setLights(
            event.target.checked,
            );
        };

    const handleAddPitch = () => {
        const json = JSON.stringify({
            name,
            price,
            lights,
            sector: sector.value,
            min_people: minP,
            max_people: maxP,
            numberOfBaskets
        });
        console.log(json);
        axios.post('Pitches/addBasketballPitch', json,{
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        }).then(()=>{
              showSuccess('succesful action')
          }).catch(error => {
            const message = error.response.data
            handleError(message, error.response.status)
          })
    }

    return (
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>


        <div className={ styles.body }>
            <h1>Add BasketballPitch</h1>
            <h3>BasketballPitch name:</h3>
            <TextField
                label={"BasketballPitch name *"}
                placeholder={"BasketballPitch name"}
                value={name}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setName(event.target.value)
                }}>
            </TextField>
            <h3>Price:</h3>
            <TextField
                label={"Price *"}
                placeholder={"Price"}
                value={price}
                style={{
                    marginTop: '16px'}}
                type="number"
                onChange={event => {
                    setPrice(event.target.value)
                }}
                min="0">
            </TextField>
            <h3>Lights:</h3>
            <FormControlLabel
                control={
                    <Switch checked={lights} onChange={handleChangeLights} name="lights" />
                }
                label="Lights:"
                labelPlacement="start"
                style={{
                    marginTop: '16px'}}
            />
            <h3>Sector:</h3>
            <Select
            defaultValue={sector}
            onChange={setSector}
            placeholder={"Sector"}
            options={options1}
            style={{
                marginTop: '16px'}}
            width='200px'>
            </Select>
            <h3>Min. number of people:</h3>
            <TextField
                placeholder={"number of people"}
                value={minP}
                type="number"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setMinP(event.target.value)
                }}
                min="1">
            </TextField>
            <h3>Max. number of people:</h3>
            <TextField
                placeholder={"number of people"}
                value={maxP}
                type="number"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setMaxP(event.target.value)
                }}
                min="2">
            </TextField>
            <h3>Number of baskets:</h3>
            <TextField
                placeholder={"Number of baskets"}
                value={numberOfBaskets}
                type="number"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setNumberOfBaskets(event.target.value)
                }}
                min="2">
            </TextField>
            <br></br>
            <Button
                variant="success"
                style={{
                    width: '50%',
                    fontSize: '2rem',
                    padding: '10px 0',
                    marginTop: '16px',
                }}
                onClick={handleAddPitch}
            >{"+ Add"}</Button>
            </div>
        </div>
    )
}
    
export default AddBPitch
