 import { TextField } from '@mui/material';
import { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';
import useErrorHandler from "../errorHandler";
import {useSnackbarQueue} from "../components/Snackbar"

function EditAccount() {
   
    const navigate = useNavigate();

    const currentAccount = JSON.parse(sessionStorage.getItem("id"));
    const [login, setLogin] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');
    const [salary, setSalary] = useState('');
    const [numberOfShifts, setNumberOfShifts] = useState('');
    const [first_name, setFirst_name] = useState('');
    const [last_name, setLast_name] = useState('');
    const token = sessionStorage.getItem("JWTToken")
    const handleError = useErrorHandler()
    const showSuccess = useSnackbarQueue('success')
    const handleEditAdmin = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            login,
            email,
            active: currentAccount.active,
            role: currentAccount.role
        });
        console.log(json);
        axios.put(`Account/UpdateAdmin/${currentAccount.id}`, json,{
            headers: {
                "Content-Type": "application/json",
                'Authorization': `Bearer ${token}`
            //     "Accept": "application/json",
            //     // "If-Match": currentAccount.etag,
            }
        }).then(()=>{
              showSuccess('succesful action')
          }).catch(error => {
            const message = error.response.data
            handleError(message, error.response.status)
          })
    }

    const handleEditManager = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            login,
            email,
            active: currentAccount.active,
            role: currentAccount.role,
            salary,
            numberOfShifts
        });
        console.log(json);
        axios.put(`Account/UpdateManager/${currentAccount.id}`, json,{
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

    const handleEditUser = () => {
        const json = JSON.stringify({
            id: currentAccount.id,
            login,
            email,
            active: currentAccount.active,
            role: currentAccount.role,
            first_name,
            last_name
        });
        console.log(json);
        axios.put(`Account/UpdateClient/${currentAccount.id}`, json,{
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

    const getAccount = () => {
        if(currentAccount.role === "ADMINISTRATOR"){
            return axios.get(`/Account/admin/${currentAccount.id}`,{
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
        if(currentAccount.role === "MANAGER"){
            return axios.get(`/Account/manager/${currentAccount.id}`,{
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
        if(currentAccount.role === "USER"){
            return axios.get(`/Account/client/${currentAccount.id}`,{
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
        getAccount().then(res => {
            setLogin(res.data.login)
            setEmail(res.data.email)
            setRole(res.data.role)
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

    return (
       
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
        <div className={ styles.body }>
            <h1>Edit {currentAccount.role} account </h1>
            <h3>Login:</h3>
            <TextField
                label={"Login *"}
                placeholder={login}
                value={login}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setLogin(event.target.value)
                }}>
            </TextField>
            <h3>Email:</h3>
            <TextField
                label={"Email *"}
                placeholder={email}
                value={email}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setEmail(event.target.value)
                }}>
            </TextField>
            <If condition={role === "ADMINISTRATOR"}><Then>
                <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handleEditAdmin}
                >{"Edit Admin"}</Button>
            </Then></If>
            <If condition={role === "MANAGER"}><Then>
                <h3>Salary:</h3>
                <TextField
                    placeholder={salary}
                    value={salary}
                    type="number"
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setSalary(event.target.value)
                    }}
                    min="2">
                </TextField>
                <h3>Number of shifts:</h3>
                <TextField
                    placeholder={numberOfShifts}
                    value={numberOfShifts}
                    type="number"
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setNumberOfShifts(event.target.value)
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
                onClick={handleEditManager}
                >{"Edit Manager"}</Button>
            </Then></If>
            <If condition={role === "USER"}><Then>
                <h3>Name:</h3>
                <TextField
                    label={"Imię *"}
                    placeholder={first_name}
                    value={first_name}
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setFirst_name(event.target.value)
                    }}>
                </TextField>
                <h3>Surname:</h3>
                <TextField
                    label={"Nazwisko *"}
                    placeholder={last_name}
                    value={last_name}
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setLast_name(event.target.value)
                    }}>
                </TextField>
                <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handleEditUser}
                >{"Edit Client"}</Button>
            </Then></If>
            </div>
        </div>
    )
}
    
export default EditAccount
