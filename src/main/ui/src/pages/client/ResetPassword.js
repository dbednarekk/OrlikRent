import React,{ useEffect, useState } from "react";
import { TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
import styles from "../../styles/AddPitch.module.css";
import { Button } from "react-bootstrap";
import axios from "../../Services/URL";
import { If, Then } from "react-if";
import { PASSWORD_REGEX} from "../../regexConstants.ts"
import useErrorHandler from "../../errorHandler.ts";
import { useSnackbarQueue } from "../../components/Snackbar.ts";
function ResetPassword() {
  const [password, setPassword] = useState("");
  const [passwordd, setPasswordd] = useState("");
  const [passwd, setPasswd] = useState("");
  const token = JSON.parse(sessionStorage.getItem("JWTToken"))
  const handleError = useErrorHandler()
  const showSuccess = useSnackbarQueue('success')
  const login = JSON.parse(sessionStorage.getItem("Login"))
  const [etag,setEtag] = useState('')
  const [id,setID] = useState('')
  const [error, setError] = useState('');
  const getAccountDetails = () => {
    return axios.get(`Account/login/${login}`,{
      headers:{
        'Authorization': `Bearer ${token}`
      }}).then((res) => {
        console.log(res.headers.etag)
      setEtag(res.headers.etag)
      setID(res.data.id)
    }).catch(error =>{
      const message = error.response.data
      handleError(message, error.response.status)
    });
  };
  useEffect(() => {
    getAccountDetails();
  }, []);


  const handleReset =()=>{
    const errors = {}

    if(password === ''){
      errors.passwordd = 'Password is required';    
      return        
  }
   if(!PASSWORD_REGEX.test(password)){
      errors.passwordd = 'This is not valid password'
      return
  }
  if(password !== passwordd){
      errors.passwordd = 'Passwords does not match'
      return
  }

  setError(errors)

    const json = JSON.stringify({
      id: id,
      login: login,
      oldPasswd: passwd,
      newPasswd: password,
      token: token
  });
  axios.put('Account/resetPassword', json,{
      headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
          'If-Match': etag
      }
  }).then(()=>{
        showSuccess('succesful action')
    }).catch(error => {
      const message = error.response.data
      handleError(message, error.response.status)
    })
  }
  return (
    <div className={styles.body}>
      <h1>Reset Password</h1>
      <h3>Old Password:</h3>
      <TextField
        label={"Password *"}
        placeholder={"Password"}
        value={passwd}
        style={{
          marginTop: "16px",
        }}
        type="password"
        onChange={(event) => {
          setPasswd(event.target.value);
        }}
      ></TextField>
      <h3>New Password:</h3>
      <TextField
        label={"Password *"}
        placeholder={"Password"}
        value={password}
        style={{
          marginTop: "16px",
        }}
        type="password"
        onChange={(event) => {
          setPassword(event.target.value);
        }}
        min="0"
      ></TextField>
       <h3>Repeat Password:</h3>
       <TextField
                label={"Password *"}
                placeholder={"Password"}
                value={passwordd}
                style={{
                    marginTop: '16px'}}
                type="password"
                onChange={event => {
                    setPasswordd(event.target.value)
                }}
                min="0">
            </TextField>
            <p>{error.passwordd}</p>
      <Button
                    variant="success"
                    style={{
                        width: '50%',
                        fontSize: '2rem',
                        padding: '10px 0',
                        marginTop: '16px',
                    }}
                    onClick={handleReset}
                >{"Reset Password"}</Button>
    </div>
  );
}

export default ResetPassword;
