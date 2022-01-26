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

export interface PopupData {
    open: boolean,
    onCancel: () => void,
    id: string,
    role: string,
}

export default function PopupData({open, onCancel, id, role}){

    const [login, setLogin] = useState('');
    const [email, setEmail] = useState('');
    const [active, setActive] = useState(false);
    const [role1, setRole] = useState('');
    const [salary, setSalary] = useState('');
    const [numberOfShifts, setNumberOfShifts] = useState('');
    const [first_name, setFirst_name] = useState('');
    const [last_name, setLast_name] = useState('');
    const token = sessionStorage.getItem("JWTToken")
    const handleError = useErrorHandler()
    const showSuccess = useSnackbarQueue('success')
    const handleOpen = () => {
        if(role === "ADMINISTRATOR"){
            return axios.get(`/Account/admin/${id}`,{
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
        if(role === "MANAGER"){
            return axios.get(`/Account/manager/${id}`,{
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
        if(role === "USER"){
            return axios.get(`/Account/client/${id}`,{
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
    }

    useEffect( () => {
        handleOpen().then(res => {
            setLogin(res.data.login)
            setEmail(res.data.email)
            setRole(res.data.role)
            setActive(res.data.active)
            if(res.data.role === "MANAGER"){
                setSalary(res.data.salary)
                setNumberOfShifts(res.data.numberOfShifts)
            }
            if(res.data.role === "USER"){
                setFirst_name(res.data.first_name)
                setLast_name(res.data.last_name)
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
                        <td className={styletb.td}><h2>{"Login"}</h2></td>
                        <td className={styletb.td}><h2>{"Email"}</h2></td>
                        <td className={styletb.td}><h2>{"Active"}</h2></td>
                        <td className={styletb.td}><h2>{"Role"}</h2></td>
                        <If condition={role === "MANAGER"}><Then>
                        <td className={styletb.td}><h2>{"Salary"}</h2></td>
                        <td className={styletb.td}><h2>{"Number of Shifts"}</h2></td>
                        </Then></If>
                        <If condition={role === "USER"}><Then>
                        <td className={styletb.td}><h2>{"First name"}</h2></td>
                        <td className={styletb.td}><h2>{"Last name"}</h2></td>
                        </Then></If>

                    </tr>
                    <tr>
                        <td className={styletb.tdData}><h2>{login}</h2></td>
                        <td className={styletb.tdData}><h2>{email}</h2></td>
                        <td className={styletb.tdData}><h2>{'' + active}</h2></td>
                        <td className={styletb.tdData}><h2>{role1}</h2></td>
                        <If condition={role === "MANAGER"}><Then>
                        <td className={styletb.tdData}><h2>{salary}</h2></td>
                        <td className={styletb.tdData}><h2>{numberOfShifts}</h2></td>
                        </Then></If>
                        <If condition={role === "USER"}><Then>
                        <td className={styletb.tdData}><h2>{first_name}</h2></td>
                        <td className={styletb.tdData}><h2>{last_name}</h2></td>
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