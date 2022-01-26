import React from "react";
import Button from '@material-ui/core/Button';
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import { useState, useEffect } from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';
import styletb from '../styles/tableStyle.module.css'
import useErrorHandler from "../errorHandler";
import {useSnackbarQueue} from "../components/Snackbar"

export interface PopupDataPitch {
    open: boolean,
    onCancel: () => void,
    id: string,
    pitch: [],
}

export default function PopupDataPitch({open, onCancel, id, pitch}){

    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [lights, setLights] = useState(false);
    const [sector, setSector] = useState('');
    const [minP, setMinP] = useState('');
    const [maxP, setMaxP] = useState('');
    const token = sessionStorage.getItem("JWTToken")
    const [numberOfBaskets1, setNumberOfBaskets1] = useState(null);
    const handleError = useErrorHandler()
    const showSuccess = useSnackbarQueue('success')
    const [grasstype, setGrasstype] = useState('');
    const [nets1, setNets1] = useState(null);

    const handleOpen = () => {
        if(pitch.goal_nets != null){
            return axios.get(`/Pitches/footballById/${id}`,{
                headers:{
                    'Authorization': `Bearer ${token}`
                }
            } ).then(()=>{
                  showSuccess('succesful action')
              }).catch(error => {
                const message = error.response.data
                handleError(message, error.response.status)
              })
        }
        else{
            return axios.get(`/Pitches/basketballById/${id}`,{
                headers:{
                    'Authorization': `Bearer ${token}`
                }
            }).then(()=>{
                  showSuccess('succesful action')
              }).catch(error => {
                const message = error.response.data
                handleError(message, error.response.status)
              })
        }
    }

    useEffect( () => {
        handleOpen().then(res => {
            setName(res.data.name)
            setPrice(res.data.price)
            setLights(res.data.lights)
            setSector(res.data.sector)
            setMinP(res.data.min_people)
            setMaxP(res.data.max_people)
            if(res.data.numberOfBaskets != null){
                setNumberOfBaskets1(res.data.numberOfBaskets)
            }
            if(res.data.goal_nets != null){
                setGrasstype(res.data.grass_type)
                setNets1(res.data.goal_nets)
            }
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
                        <td className={styletb.td}><h2>{"Name"}</h2></td>
                        <td className={styletb.td}><h2>{"Price"}</h2></td>
                        <td className={styletb.td}><h2>{"Lights"}</h2></td>
                        <td className={styletb.td}><h2>{"Sector"}</h2></td>
                        <td className={styletb.td}><h2>{"Min. number of people"}</h2></td>
                        <td className={styletb.td}><h2>{"Max. number of people"}</h2></td>
                        <If condition={nets1 != null}><Then>
                        <td className={styletb.td}><h2>{"Grass type"}</h2></td>
                        <td className={styletb.td}><h2>{"Nets"}</h2></td>
                        </Then></If>
                        <If condition={numberOfBaskets1 != null}><Then>
                        <td className={styletb.td}><h2>{"Number of baskets"}</h2></td>
                        </Then></If>

                    </tr>
                    <tr>
                        <td className={styletb.tdData}><h2>{name}</h2></td>
                        <td className={styletb.tdData}><h2>{price}</h2></td>
                        <td className={styletb.tdData}><h2>{'' + lights}</h2></td>
                        <td className={styletb.tdData}><h2>{sector}</h2></td>
                        <td className={styletb.tdData}><h2>{minP}</h2></td>
                        <td className={styletb.tdData}><h2>{maxP}</h2></td>
                        <If condition={nets1 != null}><Then>
                        <td className={styletb.tdData}><h2>{grasstype}</h2></td>
                        <td className={styletb.tdData}><h2>{'' + nets1}</h2></td>
                        </Then></If>
                        <If condition={numberOfBaskets1 != null}><Then>
                        <td className={styletb.tdData}><h2>{numberOfBaskets1}</h2></td>
                        </Then></If>
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
