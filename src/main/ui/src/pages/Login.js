
import { TextField } from '@mui/material';
import { useState } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import useErrorHandler from '../errorHandler.ts';

function Login() {
    const navigate = useNavigate();
    const handleError = useErrorHandler()
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    function parseJwt (token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    
        return JSON.parse(jsonPayload);
    };
    const handleAddUser = async() => {
        const json = JSON.stringify({
            login,
            password,
           
        });
         await axios.post('auth/login', json,{
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((res) => {
            sessionStorage.setItem("JWTToken", JSON.stringify(res.data))
            const cred = parseJwt(res.data)
            sessionStorage.setItem("Login",JSON.stringify(cred.sub))
            sessionStorage.setItem("Auth",JSON.stringify(cred.auth))
            axios.get(`auth/self/${cred.sub}`).then((res) => {
                  sessionStorage.setItem("ID",JSON.stringify(res.data.id))
                  })
           navigate("/")
           
          }).catch(error =>{
            const message = error.response.data
            handleError(message, error.response.status)
          });
    }
    return (
       
        <div style={{ margin: '50px' }}> 
        <button onClick={() => navigate(-1)}>Back</button>
        <div className={ styles.body }>
            <h1>Log in</h1>
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
            <h3>Password:</h3>
            <TextField
                label={"Password *"}
                placeholder={"Password"}
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
                >{"Login"}</Button>
            </div>
        </div>
    )

}

export default Login;
