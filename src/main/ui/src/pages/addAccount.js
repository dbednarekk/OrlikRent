 import { TextField } from '@mui/material';
import { useState } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';

function AddAccount() {
   
    const navigate = useNavigate();


    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    // const [active, setActive] = useState(false);
    const [role, setRole] = useState('');
    const [salary, setSalary] = useState('');
    const [numberOfShifts, setNumberOfShifts] = useState('');
    const [first_name, setFirst_name] = useState('');
    const [last_name, setLast_name] = useState('');
    
    // const handleChangeActive = (event) => {
    //     setActive(
    //         event.target.checked,
    //         );
    //     };

    const handleAddAdmin = () => {
        const json = JSON.stringify({
            login,
            password,
            email,
            // active,
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
            // active,
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
            // active,
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
    return (
       
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
        <div className={ styles.body }>
            <h1>Dodaj konto</h1>
            <h3>Login:</h3>
            <TextField
                label={"Login *"}
                placeholder={"Login"}
                value={login}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setLogin(event.target.value)
                }}>
            </TextField>
            <h3>Hasło:</h3>
            <TextField
                label={"Hasło *"}
                placeholder={"Hasło"}
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
                placeholder={"Email"}
                value={email}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setEmail(event.target.value)
                }}>
            </TextField>
            {/* <h3>Aktywne:</h3>
            <FormControlLabel
                control={
                    <Switch checked={active} onChange={handleChangeActive} name="Active" />
                }
                label="Aktywne:"
                labelPlacement="start"
                style={{
                    marginTop: '16px'}}
            /> */}
            <h3>Rola:</h3>
            <TextField
                label={"Rola *"}
                placeholder={"Rola"}
                value={role}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setRole(event.target.value)
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
                    onClick={handleAddAdmin}
                >{"+ Dodaj Admina"}</Button>
            </Then></If>
            <If condition={role === "MANAGER"}><Then>
                <h3>Zarobki:</h3>
                <TextField
                    placeholder={"Zarobki"}
                    value={salary}
                    type="number"
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setSalary(event.target.value)
                    }}
                    min="2">
                </TextField>
                <h3>Liczba zmian:</h3>
                <TextField
                    placeholder={"Liczba zmian"}
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
                >{"+ Dodaj Managera"}</Button>
            </Then></If>
            <If condition={role === "USER"}><Then>
                <h3>Imię:</h3>
                <TextField
                    label={"Imię *"}
                    placeholder={"Imię"}
                    value={first_name}
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setFirst_name(event.target.value)
                    }}>
                </TextField>
                <h3>Nazwisko:</h3>
                <TextField
                    label={"Nazwisko *"}
                    placeholder={"Nazwisko"}
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
                >{"+ Dodaj Klienta"}</Button>
            </Then></If>
            </div>
        </div>
    )
}
    
export default AddAccount
