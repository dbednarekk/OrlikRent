 import { TextField } from '@mui/material';
import { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import Header from "../components/Header";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import React from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';
import Select from 'react-select';
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import useErrorHandler from "../errorHandler.ts";
import {useSnackbarQueue} from "../components/Snackbar.ts"
import {  NAME_REGEX , NUMBER_REGEX} from "../regexConstants.ts"


function EditPitch() {

    const navigate = useNavigate();
    const token = sessionStorage.getItem("JWTToken")
    const currentAccount = JSON.parse(sessionStorage.getItem("pitch"));
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [lights, setLights] = useState(false);
    const [sector, setSector] = useState('');
    const [minP, setMinP] = useState('');
    const [maxP, setMaxP] = useState('');
    const [error, setError] = useState('');
    const [etag,setEtag] = useState('')

    const handleError = useErrorHandler()
    const showSuccess = useSnackbarQueue('success')

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
                }else if(parseInt(maxP) < parseInt(minP)){
                    errors.maxP = 'Max number of people must be bigger then min number of people'
                }
                if(currentAccount.goal_nets != null){
                    if(grasstype == ''){
                        errors.grasstype = 'Grass type is required';            
                    }
                    setError(errors)
                    if(Object.keys(errors).length === 0){
                    handleEditFootball();
                    }
                }

                if(currentAccount.numberOfBaskets != null){
                    if(numberOfBaskets == ''){
                        errors.numberOfBaskets = 'Number of baskets  is required';            
                    }else if(!NUMBER_REGEX.test(numberOfBaskets)){
                        errors.numberOfBaskets = 'This is not valid number of baskets'
                    }
                    setError(errors)
                    if(Object.keys(errors).length === 0){
                    handleEditBasketball()
                    }
                }
        }
        


    const handleEditFootball = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            name,
            price,
            lights,
            sector: sector.value,
            min_people: minP,
            max_people: maxP,
            rented: currentAccount.rented,
            grass_type: grasstype.value,
            goal_nets: nets

        });
        axios.put(`Pitches/FootballPitch/${currentAccount.id}`, json,{
            headers: {
                "Content-Type": "application/json",
                'Authorization': `Bearer ${token}`,
            //     "Accept": "application/json",
                "If-Match": etag,
            }
        }).then(()=>{
              showSuccess('succesful action')
          }).catch(error => {
            const message = error.response.data
            handleError(message, error.response.status)
          })
    }

    const handleEditBasketball = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            name,
            price,
            lights,
            sector: sector.value,
            min_people: minP,
            max_people: maxP,
            rented: currentAccount.rented,
            numberOfBaskets,
        });
        axios.put(`Pitches/BasketballPitch/${currentAccount.id}`, json,{
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
                "If-Match": etag,

            }
        }).then(()=>{
              showSuccess('succesful action')
          }).catch(error => {
            const message = error.response.data
            handleError(message, error.response.status)
          })
    }

    const getPitch = () => {
        if(currentAccount.goal_nets != null){
            return axios.get(`/Pitches/footballById/${currentAccount.id}`, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }}).then((res)=>{
                      setEtag(res.headers.etag)
                  }).catch(error => {
                    const message = error.response.data
                    handleError(message, error.response.status)
                  })
        }
        if(currentAccount.numberOfBaskets != null){
            return axios.get(`/Pitches/basketballById/${currentAccount.id}`, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }}).then((res)=>{
                      setEtag(res.headers.etag)
                      showSuccess('succesful action')
                  }).catch(error => {
                    const message = error.response.data
                    handleError(message, error.response.status)
                  })
        }
        else{
            return axios.get(`/Pitches/${currentAccount.id}`, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }}).then((res)=>{
                      setEtag(res.headers.etag)
                      showSuccess('succesful action')
                  }).catch(error => {
                    const message = error.response.data
                    handleError(message, error.response.status)
                  })
        }
    }

    useEffect( async () => {
        await getPitch().then(res => {
            setName(res.data.name)
            setPrice(res.data.price)
            setLights(res.data.lights)
            setSector(res.data.sector)
            setMinP(res.data.min_people)
            setMaxP(res.data.max_people)
            if (currentAccount.numberOfBaskets != null) {
                setNumberOfBaskets(res.data.numberOfBaskets)
            }
            if (currentAccount.goal_nets != null) {
                setGrasstype(res.data.grass_type)
                setNets(res.data.goal_nets)
            }
        })
    }, [])

    return (

        <div style={{ margin: '50px' }}>
        <Header title="Edit Pitch" />
         <ArrowBackIcon style={{ marginTop: '75px' }} onClick={() => navigate(-1)}/>
        <div className={ styles.body }>
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
            <p>{error.name}</p>
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
            placeholder={sector}
            options={options1}
            style={{
                marginTop: '16px'}}
            width='200px'>
            </Select>
            <p>{error.sector}</p>
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
            <p>{error.minP}</p>
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
            <p>{error.maxP}</p>
            <If condition={currentAccount.goal_nets != null}><Then>
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
            <p>{error.grasstype}</p>
            <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handlevalidation}
                >{"Edit pitch"}</Button>
            </Then></If>
            <If condition={currentAccount.numberOfBaskets != null}><Then>
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
            <p>{error.numberOfBaskets}</p>
            <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handlevalidation}
                >{"Edit pitch"}</Button>
            </Then></If>
            </div>
        </div>
    )
}

export default EditPitch
