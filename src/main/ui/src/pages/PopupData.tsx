import React from "react";
import Button from '@material-ui/core/Button';
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import { useState, useEffect } from "react";
import axios from "../Services/URL";
import { If, Then } from 'react-if';
import styletb from '../styles/tableStyle.module.css'
import Box from "@mui/material/Box";
import styles from "../styles/FootballPitch.module.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import ActiveIcon from "../components/ActiveIcon";

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


    function Row(props) {
        const { row } = props;
    
        return (
            <React.Fragment>
                <TableRow>
                    <TableCell align="center" component="th" scope="row">
                        {row.id}
                    </TableCell>
                    <TableCell align="center">{row.accountID}</TableCell>
                    <TableCell align="center">{row.pitchID}</TableCell>
                    <TableCell align="center">{row.start_date_rental}</TableCell>
                    <TableCell align="center">{row.end_date_rental}</TableCell>
                    <TableCell align="center">
                        <ActiveIcon active={row.active} />
                    </TableCell>
                </TableRow>
            </React.Fragment>
        );
    }
    
    function BasicTable() {
        const [rentals, setRentals] = useState([]);
        const getRentals = () => {
            return axios.get(`Rentals/RentsForClient/${login}`,{
                headers:{
                    'Authorization': `Bearer ${token}`
                }})
        }
    
        const getUpdatedRentals = () => {
            return getRentals().then((res) => {
                setRentals(res.data);
            })
        };
        useEffect(() => {
            getUpdatedRentals();
        }, []);
        const rnts = [];
        const [searchInput, setSearchInput] = useState("");
    
        function search(rows) {
            if (Array.isArray(rows) && rows.length) {
                const filteredRent = rows.filter(
                    (row) =>
                        row.props.row.id.concat(" ",row.props.row.accountID).concat(" ", row.props.row.pitchID).toLowerCase().indexOf(searchInput.toLowerCase()) > -1
                );
    
                filteredRent.forEach((rent) =>
                    rnts.includes(rent.props.row.id + " " + rent.props.row.accountID + " " + rent.props.row.pitchID) ? "" : rnts.push(rent.props.row.id)
                );
                return filteredRent;
            } else {
                return rows;
            }
        }
        return (
            <Box
                style={{
                    position: "relative",
                    top: "20%",
                }}
            >
                <TableContainer component={Paper} className={styles.table}>
                    <Table aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell />
                                <TableCell align="center">id</TableCell>
                                <TableCell align="center">account ID</TableCell>
                                <TableCell align="center">pitch ID</TableCell>
                                <TableCell align="center">start Date</TableCell>
                                <TableCell align="center">end Date</TableCell>
                                <TableCell align="center">active</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {search(
                                rentals.map((row, index) => (
                                    <Row key={index} row={row} onChange={getUpdatedRentals} />
                                ))
                            )}
                        </TableBody>
                    </Table>
                </TableContainer>
            </Box>
        );
    }



    const handleOpen = () => {
        if(role === "ADMINISTRATOR"){
            return axios.get(`/Account/admin/${id}`,{
                headers:{
                    'Authorization': `Bearer ${token}`
                }
            } )
        }
        if(role === "MANAGER"){
            return axios.get(`/Account/manager/${id}`,{
                headers:{
                    'Authorization': `Bearer ${token}`
                }
            } )
        }
        if(role === "USER"){
            return axios.get(`/Account/client/${id}`,{
                headers:{
                    'Authorization': `Bearer ${token}`
                }
            } )
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
                    <BasicTable />
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