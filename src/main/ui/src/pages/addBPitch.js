 import { TextField } from '@mui/material';
import { useState } from "react";
import {Link} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React, { Component } from "react";
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import Select from 'react-select';
import {makeStyles} from '@mui/styles';
import { green } from '@mui/material/colors';
import axios from "../Services/URL";

function AddBPitch() {
   

    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [lights, setLights] = useState(false);
    const [sector, setSector] = useState('');
    const [minP, setMinP] = useState('');
    const [maxP, setMaxP] = useState('');
    const [numberOfBaskets, setNumberOfBaskets] = useState('');

    const options1 = [
        { value: 'FULL_SIZE', label: 'Pełne' },
        { value: 'HALF_SIZE', label: 'Połowa' },
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
                'Content-Type': 'application/json'
            }
        })
    }

    return (
        <div style={{ margin: '50px' }}> 
        <Link to="/basketballPitch/">
        <p>Cofnij</p>
        </Link>

        <div className={ styles.body }>
            <h1>Dodaj boisko koszykarskie</h1>
            <h3>Nazwa boiska:</h3>
            <TextField
                label={"Nazwa *"}
                placeholder={"Nazwa"}
                value={name}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setName(event.target.value)
                }}>
            </TextField>
            <h3>Cena:</h3>
            <TextField
                label={"Cena *"}
                placeholder={"Cena"}
                value={price}
                style={{
                    marginTop: '16px'}}
                type="number"
                onChange={event => {
                    setPrice(event.target.value)
                }}
                min="0">
            </TextField>
            <h3>Oświetlenie:</h3>
            <FormControlLabel
                control={
                    <Switch checked={lights} onChange={handleChangeLights} name="lights" />
                }
                label="Włącz:"
                labelPlacement="start"
                style={{
                    marginTop: '16px'}}
            />
            <h3>Sektor:</h3>
            <Select
            defaultValue={sector}
            onChange={setSector}
            placeholder={"Sektor"}
            options={options1}
            style={{
                marginTop: '16px'}}
            width='200px'>
            </Select>
            <h3>Min. liczba osób:</h3>
            <TextField
                placeholder={"Liczba osób"}
                value={minP}
                type="number"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setMinP(event.target.value)
                }}
                min="1">
            </TextField>
            <h3>Max. liczba osób:</h3>
            <TextField
                placeholder={"Liczba osób"}
                value={maxP}
                type="number"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setMaxP(event.target.value)
                }}
                min="2">
            </TextField>
            <h3>Ilość koszy:</h3>
            <TextField
                placeholder={"Liczba koszy"}
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
            >{"+ Dodaj"}</Button>
            </div>
        </div>
    )
}
    
export default AddBPitch
