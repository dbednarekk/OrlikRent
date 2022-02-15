import { TextField } from '@mui/material';
import { useState } from "react";
import {useNavigate} from "react-router-dom";
import styles from '../styles/AddPitch.module.css'
import { Button } from 'react-bootstrap';
import React from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';
import Header from "../components/Header";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import useErrorHandler from "../errorHandler.ts";
import {useSnackbarQueue} from "../components/Snackbar.ts"
import { LOGIN_REGEX, PASSWORD_REGEX , EMAIL_REGEX , NAME_REGEX , NUMBER_REGEX} from "../regexConstants.ts"

function AddAccount() {
   
    const navigate = useNavigate();

  // const [active, setActive] = useState(false);
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const [passwd, setPasswd] = useState('');
    const [email, setEmail] = useState('');
    const [role, setRole] = useState('');
    const [salary, setSalary] = useState('');
    const [numberOfShifts, setNumberOfShifts] = useState('');
    const [first_name, setFirst_name] = useState('');
    const [last_name, setLast_name] = useState('');
    const [error, setError] = useState('');
    const token = sessionStorage.getItem("JWTToken")
    const handleError = useErrorHandler()
    const showSuccess = useSnackbarQueue('success')
    // const handleChangeActive = (event) => {
    //     setActive(
    //         event.target.checked,
    //         );
    //     };



    const handlevalidation = () => {
      const errors = {}
        if(login == ''){
            errors.login = 'Login is required';            
        }else if(!LOGIN_REGEX.test(login)){
            errors.login = 'This is not valid login'
        }
        if(password == ''){
            errors.password = 'Password is required';            
        }else if(!PASSWORD_REGEX.test(password)){
            errors.password = 'This is not valid password'
        }
        if(password != passwd){
            errors.passwd = 'Passwords does not match'
        }
        if(email == ''){
            errors.email = 'Email is required';            
        }else if(!EMAIL_REGEX.test(email)){
            errors.email = 'This is not valid email'
        }
        if(role === 'MANAGER'){
            if(salary == ''){
                errors.salary = 'Salary is required';            
            }else if(!NUMBER_REGEX.test(salary)){
                errors.salary = 'This is not valid salary'
            }
            if(numberOfShifts == ''){
                errors.numberOfShifts = 'Number of shifts is required';            
            }else if(!NUMBER_REGEX.test(numberOfShifts)){
                errors.numberOfShifts = 'This is not valid number of shifts'
            }
        }
        if(role === 'USER'){
            if(first_name == ''){
                errors.first_name = 'First name is required';            
            }else if(!NAME_REGEX.test(first_name)){
                errors.first_name = 'This is not valid first name'
            }
            if(last_name == ''){
                errors.last_name = 'Last name is required';            
            }else if(!NAME_REGEX.test(last_name)){
                errors.last_name = 'This is not valid last name'
            }
        }
        setError(errors)
        if(Object.keys(errors).length === 0){
            if(role === 'MANAGER'){
                handleAddManager();
            }
            if(role === 'USER'){
                handleAddUser();
            }
            if(role === 'ADMINISTRATOR'){
                handleAddAdmin();
            }
        }
    }


    const handleAddAdmin = () => {
        const json = JSON.stringify({
            login,
            password,
            email,
            // active,
            role
        });
        axios.post('auth/register/admin', json,{
            headers: {
                'Content-Type': 'application/json'
              
            }
        }).then(()=>{
              showSuccess('succesful action')
          }).catch(error => {
            const message = error.response.data
            handleError(message, error.response.status)
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
        axios.post('auth/register/manager', json,{
            headers: {
                'Content-Type': 'application/json'
               
            }
        }).then(()=>{
              showSuccess('succesful action')
          }).catch(error => {
            const message = error.response.data
            handleError(message, error.response.status)
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
        axios.post('auth/register/client', json,{
            headers: {
                'Content-Type': 'application/json'
              
            }
        }).then(()=>{
              showSuccess('succesful action')
          }).catch(error => {
            const message = error.response.data
            handleError(message, error.response.status)
          })
    }
    return (
       
        <div style={{ margin: '50px' }}> 
        <Header title="Register" />
         <ArrowBackIcon style={{ marginTop: '75px' }} onClick={() => navigate(-1)}/>
        <div className={ styles.body }>
            <h3>Login:</h3>
            <TextField
                label={"Login *"}
                placeholder={"Login"}
                value={login}
                pattern="[a-zA-Z]"
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setLogin(event.target.value)
                }}>
            </TextField>
            <p>{error.login}</p>
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
            <p>{error.password}</p>
            <h3>Repeat Password:</h3>
            <TextField
                label={"Password *"}
                placeholder={"Password"}
                value={passwd}
                style={{
                    marginTop: '16px'}}
                type="password"
                onChange={event => {
                    setPasswd(event.target.value)
                }}
                min="0">
            </TextField>
            <p>{error.passwd}</p>
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
            <p>{error.email}</p>
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
            <h3>Role:</h3>
            <TextField
                label={"Role *"}
                placeholder={"Role"}
                value={role}
                style={{
                    marginTop: '16px'}}
                onChange={event => {
                    setRole(event.target.value)
                }}>
            </TextField>
            {/* <If condition={role === "ADMINISTRATOR"}><Then>
                <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handlevalidation}
                >{"+ Dodaj Admina"}</Button>
            </Then></If>
            <If condition={role === "MANAGER"}><Then>
                <h3>Salary:</h3>
                <TextField
                    placeholder={"Salary"}
                    value={salary}
                    type="number"
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setSalary(event.target.value)
                    }}
                    min="2">
                </TextField>
                <p>{error.salary}</p>
                <h3>Number of shifts:</h3>
                <TextField
                    placeholder={"Number of shifts"}
                    value={numberOfShifts}
                    type="number"
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setNumberOfShifts(event.target.value)
                    }}
                    min="2">
                </TextField>
                <p>{error.numberOfShifts}</p>
                <Button
                variant="success"
                style={{
                    width: '50%',
                    fontSize: '2rem',
                    padding: '10px 0',
                    marginTop: '16px',
                }}
                onClick={handlevalidation}
                >{"+ Dodaj Managera"}</Button>
            </Then></If> */}
            <If condition={role === "USER"}><Then>
                <h3>Name:</h3>
                <TextField
                    label={"Name *"}
                    placeholder={"Name"}
                    value={first_name}
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setFirst_name(event.target.value)
                    }}>
                </TextField>
                <p>{error.first_name}</p>
                <h3>Surname:</h3>
                <TextField
                    label={"Surname *"}
                    placeholder={"Surname"}
                    value={last_name}
                    style={{
                        marginTop: '16px'}}
                    onChange={event => {
                        setLast_name(event.target.value)
                    }}>
                </TextField>
                <p>{error.last_name}</p>
                <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handlevalidation}
                >{"+ Add Client"}</Button>
            </Then></If>
            </div>
        </div>
    )
}
    
export default AddAccount
