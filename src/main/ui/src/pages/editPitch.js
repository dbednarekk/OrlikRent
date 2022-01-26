 import { TextField } from '@mui/material';
import { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';
import Select from 'react-select';
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';

function EditPitch() {
   
    const navigate = useNavigate();

    const currentAccount = JSON.parse(sessionStorage.getItem("pitch"));
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [lights, setLights] = useState(false);
    const [sector, setSector] = useState('');
    const [minP, setMinP] = useState('');
    const [maxP, setMaxP] = useState('');

    const [numberOfBaskets, setNumberOfBaskets] = useState(null);

    const [grasstype, setGrasstype] = useState('');
    const [nets, setNets] = useState(null);


    const options1 = [
        { value: 'FULL_SIZE', label: 'Full' },
        { value: 'HALF_SIZE', label: 'Half' },
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


    const handleEditFootball = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            name,
            price,
            lights,
            sector,
            min_people: minP,
            max_people: maxP,
            rented: currentAccount.rented,
            grass_type: grasstype,
            goal_nets: nets

        });
        console.log(json);
        axios.put(`Pitches/FootballPitch/${currentAccount.id}`, json,{
            headers: {
                "Content-Type": "application/json",
            //     "Accept": "application/json",
            //     // "If-Match": currentAccount.etag,
            }
        })
    }

    const handleEditBasketball = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            name,
            price,
            lights,
            sector,
            min_people: minP,
            max_people: maxP,
            rented: currentAccount.rented,
            numberOfBaskets,
        });
        console.log(json);
        axios.put(`Pitches/BasketballPitch/${currentAccount.id}`, json,{
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }

    const getPitch = () => {
        if(currentAccount.goal_nets != null){
            return axios.get(`/Pitches/footballById/${currentAccount.id}`, )
        }
        if(currentAccount.numberOfBaskets != null){
            return axios.get(`/Pitches/basketballById/${currentAccount.id}`, )
        }
        else{
            return axios.get(`/Pitches/${currentAccount.id}`, )
        }
    }

    useEffect( () => {
        getPitch().then(res => {
            setName(res.data.name)
            setPrice(res.data.price)
            setLights(res.data.lights)
            setSector(res.data.sector)
            setMinP(res.data.min_people)
            setMaxP(res.data.max_people)
            if(currentAccount.numberOfBaskets != null){
                setNumberOfBaskets(res.data.numberOfBaskets)
            }
            if(currentAccount.goal_nets != null){
                setGrasstype(res.data.grass_type)
                setNets(res.data.goal_nets)
            }
        })
    }, [])

    return (
       
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
        <div className={ styles.body }>
            <h1>Edit pitch</h1>
            <h3>Name:</h3>
            <TextField
                label={"Name *"}
                placeholder={name}
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
                placeholder={price}
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
            placeholder={sector}
            options={options1}
            style={{
                marginTop: '16px'}}
            width='200px'>
            </Select>
            <h3>Min. number of people:</h3>
            <TextField
                placeholder={minP}
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
                placeholder={maxP}
                value={maxP}
                type="number"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setMaxP(event.target.value)
                }}
                min="2">
            </TextField>
            <If condition={nets != null}><Then>
            <h3>Nets:</h3>
            <FormControlLabel
                control={
                    <Switch checked={nets} onChange={handleChangeNets} name="nets" />
                }
                label="Nets:"
                labelPlacement="start"
                style={{
                    marginTop: '16px'}}
            />
            <h3>Type of grass:</h3>
            <Select
            defaultValue={grasstype}
            onChange={setGrasstype}
            placeholder={grasstype}
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
                    onClick={handleEditFootball}
                >{"Edit pitch"}</Button>
            </Then></If>
            <If condition={numberOfBaskets != null}><Then>
            <h3>Number of baskets:</h3>
            <TextField
                placeholder={numberOfBaskets}
                value={numberOfBaskets}
                type="number"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setNumberOfBaskets(event.target.value)
                }}
                min="2">
            </TextField>
            <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handleEditBasketball}
                >{"Edit pitch"}</Button>
            </Then></If>
            </div>
        </div>
    )
}
    
export default EditPitch
