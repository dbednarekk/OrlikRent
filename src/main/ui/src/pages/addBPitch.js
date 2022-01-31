 import { TextField } from '@mui/material';
import { useState } from "react";
import { useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import Select from 'react-select';
import {  NAME_REGEX , NUMBER_REGEX} from "../regexConstants.ts"
import useErrorHandler from "../errorHandler.ts";
import {useSnackbarQueue} from "../components/Snackbar.ts"
import axios from "../Services/URL";

function AddBPitch() {
   
    const navigate = useNavigate();


    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [lights, setLights] = useState(false);
    const [sector, setSector] = useState('');
    const [minP, setMinP] = useState('');
    const [maxP, setMaxP] = useState('');
    const [error, setError] = useState('');
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


        const handlevalidation = () => {
            const errors = {}
                if(name == ''){
                    errors.name = 'Name is required';            
                }else if(!NAME_REGEX.test(name)){
                    errors.name = 'This is not valid name'
                }
                if(price == ''){
                    errors.price = 'Price is required';            
                }else if(!NUMBER_REGEX.test(price)){
                    errors.price = 'This is not valid price'
                }
                if(sector == ''){
                    errors.sector = 'Sector is required';            
                }
                if(minP == ''){
                    errors.minP = 'Min number of people is required';            
                }else if(!NUMBER_REGEX.test(minP)){
                    errors.minP = 'This is not valid min number of people'
                }
                if(maxP == ''){
                    errors.maxP = 'Max number of people is required';            
                }else if(!NUMBER_REGEX.test(maxP)){
                    errors.maxP = 'This is not valid max number of people'
                }else if(maxP < minP){
                    errors.maxP = 'Max number of people must be bigger then min number of people'
                }

                if(numberOfBaskets == ''){
                    errors.numberOfBaskets = 'Number of baskets  is required';            
                }else if(!NUMBER_REGEX.test(numberOfBaskets)){
                    errors.numberOfBaskets = 'This is not valid number of baskets'
                }
              
              setError(errors)
              if(Object.keys(errors).length === 0){
                handleAddPitch()
              }
          }



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
            <p>{error.name}</p>
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
            <p>{error.price}</p>
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
            <p>{error.sector}</p>
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
            <p>{error.minP}</p>
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
            <p>{error.maxP}</p>
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
            <p>{error.numberOfBaskets}</p>
            <br></br>
            <Button
                variant="success"
                style={{
                    width: '50%',
                    fontSize: '2rem',
                    padding: '10px 0',
                    marginTop: '16px',
                }}
                onClick={handlevalidation}
            >{"+ Add"}</Button>
            </div>
        </div>
    )
}
    
export default AddBPitch
