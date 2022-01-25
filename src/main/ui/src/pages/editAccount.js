 import { TextField } from '@mui/material';
import { useState, useEffect } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import { If, Then } from 'react-if';

function EditAccount() {
   
    const navigate = useNavigate();

    const currentAccount = JSON.parse(sessionStorage.getItem("id"));
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [active, setActive] = useState(false);
    const [role, setRole] = useState('');
    const [salary, setSalary] = useState('');
    const [numberOfShifts, setNumberOfShifts] = useState('');
    const [first_name, setFirst_name] = useState('');
    const [last_name, setLast_name] = useState('');
    const [Account, setAccount] = useState('');


    const handleChangeActive = (event) => {
        setActive(
            event.target.checked,
            );
        };

    const handleAddAdmin = () => {
        const json = JSON.stringify({
            login,
            password,
            email,
            active,
            role
        });
        console.log(json);
        axios.post('Account/admin', json,{
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }

    const handleAddManager = () => {
        const json = JSON.stringify({
            login,
            password,
            email,
            active,
            role,
            salary,
            numberOfShifts
        });
        console.log(json);
        axios.post('Account/manager', json,{
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }

    const handleAddUser = () => {
        const json = JSON.stringify({
            login,
            password,
            email,
            active,
            role,
            first_name,
            last_name
        });
        console.log(json);
        axios.post('Account/client', json,{
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }

    const getAccount = () => {
        console.log(currentAccount.role)
        if(currentAccount.role === "ADMINISTRATOR"){
            return axios.get(`/Account/admin/${currentAccount.id}`, )
        }
    }

    useEffect( () => {
        getAccount().then(res => {
            setLogin(res.data.login)
            setPassword(res.data.password)
            setEmail(res.data.email)
            setActive(res.data.active)
            setRole(res.data.role)
        //     if(res.data.role === "MANAGER"){
        //         setSalary(res.data.salary)
        //         setNumberOfShifts(res.data.numberOfShifts)
        //     }
        //     if(res.data.role === "USER"){
        //         setFirst_name(res.data.first_name)
        //         setLast_name(res.data.last_name)
        //     }
        })
    })

    return (
       
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
        <div className={ styles.body }>
            <h1>Edit account</h1>
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
            <h3>Password:</h3>
            <TextField
                label={"Password *"}
                placeholder={password}
                value={password}
                style={{
                    marginTop: '16px'}}
                type="password"
                onChange={event => {
                    setPassword(event.target.value)
                }}
                min="0">
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
            <h3>Active:</h3>
            <FormControlLabel
                control={
                    <Switch checked={active} onChange={handleChangeActive} name="Active" />
                }
                label="Active:"
                labelPlacement="start"
                style={{
                    marginTop: '16px'}}
            />
            <If condition={role === "ADMINISTRATOR"}><Then>
                <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handleAddAdmin}
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
                onClick={handleAddManager}
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
                    onClick={handleAddUser}
                >{"Edit Client"}</Button>
            </Then></If>
            </div>
        </div>
    )
}
    
export default EditAccount
