import { TextField } from '@mui/material';
import { useState } from "react";
import { useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import Select from 'react-select';

import axios from "../Services/URL";


function AddFPitch() {

    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [minP, setMinP] = useState('');
    const [maxP, setMaxP] = useState('');
    const [sector, setSector] = useState('');
    const [grasstype, setGrasstype] = useState('');

    const [lights, setLights] = useState(false);
    const [nets, setNets] = useState(false);
    const options1 = [
        { value: 'FULL_SIZE', label: 'Pełne' },
        { value: 'HALF_SIZE', label: 'Połowa' },
      ]

    const options2 = [
        { value: 'SILICONE', label: 'Silicone' },
        { value: 'GRANULATE', label: 'Grabulate' },
        { value: 'GRASS', label: 'Grass' }
      ]
    const handleChangeLights = (event) => {
        setLights(
            event.target.checked,
          );
      };
      const handleChangeNets = (event) => {
        setNets(
            event.target.checked,
          );
      };
      const token = sessionStorage.getItem("JWTToken")
    const handleAddPitch = () => {
        const json = JSON.stringify({
            name,
            price,
            lights,
            sector: sector.value,
            min_people: minP,
            max_people: maxP,
            goal_nets: nets,
            grass_type: grasstype.value
        });
        console.log(json);
        axios.post('Pitches/addFootballPitch', json,{
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
    }

    return (
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
       

        <div className={ styles.body }>
            <h1>Dodaj boisko piłkarskie</h1>
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
            <h3>Siatki na bramce:</h3>
            <FormControlLabel
                control={
                    <Switch checked={nets} onChange={handleChangeNets} name="nets" />
                }
                label="Załóż:"
                labelPlacement="start"
                style={{
                    marginTop: '16px'}}
            />
            <h3>Typ nawierzchni:</h3>
            <Select
            defaultValue={grasstype}
            onChange={setGrasstype}
            placeholder={"Typ nawierzchni"}
            options={options2}
            style={{
                marginTop: '16px'}}
            width='200px'>
            </Select>
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

export default AddFPitch
