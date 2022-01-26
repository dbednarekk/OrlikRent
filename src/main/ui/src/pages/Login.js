
import { TextField } from '@mui/material';
import { useState } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import useErrorHandler from '../errorHandler';
function Login() {
    const navigate = useNavigate();
    const handleError = useErrorHandler()
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
   
    const handleAddUser = () => {
        const json = JSON.stringify({
            login,
            password,
           
        });
        console.log(json);
        axios.post('auth/login', json,{
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((res) => {
            sessionStorage.setItem("JWTToken", JSON.stringify(res.data))
            navigate('/');
          }).catch(error =>{
            const message = error.response.data
            handleError(message, error.response.status)
          });
    }
    return (
       
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
        <div className={ styles.body }>
            <h1>Zaloguj się</h1>
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

                <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handleAddUser}
                >{"Zaloguj"}</Button>
            </div>
        </div>
    )

}

export default Login;
