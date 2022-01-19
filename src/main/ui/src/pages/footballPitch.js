import React, {useEffect, useReducer, useState} from 'react';
import Box from '@mui/material/Box'
import Header from '../components/Header'
import Footer from '../components/Footer'
import styles from '../styles/FootballPitch.module.css'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import axios from "../Services/URL";
import PropTypes from 'prop-types';
import ActiveIcon from "../components/ActiveIcon";
import IconButton from '@mui/material/IconButton';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import Collapse from '@mui/material/Collapse';
import BaseButton from '../components/BaseButton';
import {makeStyles} from '@mui/styles';
  function createData(id, name, price, lights, sector, min_people, max_people, rented) {
    return { id, name, price, lights, sector, min_people, max_people, rented};
  }
  const useRowStyles = makeStyles({
    root: {
        '& > *': {
            borderBottom: 'unset',
        },
    },
});
function Row (props) {
    const {row} = props;
    const {onChange} = props;
    const [open, setOpen] = React.useState(false);
    const [rentals, setRentals] = React.useState(false);
    const [rent,setRent] = React.useState(false);
    const handleSetOpen = async () => {
      setOpen(state => !state);
    
    }
    const classes = useRowStyles();
    const [buttonEnable,setButtonEnable] = React.useState('true')
    const handleSetRent = ()=>{
      setRent(state => !state)
    
      const json = JSON.stringify({
        accountID: '71176e64-e76b-405f-84dc-c8a2f299a7b8',
        pitchID: row.id,
        start_date_rental: '2022-01-18T17:47:20.361',
        end_date_rental: '2022-01-20T17:47:20.361',
        active: true
      })
      return axios.post('/Rentals/addRent/',json,{
        headers:{
        'Content-Type': 'application/json'
        }
      })
    }
    return (
      <React.Fragment>
        <TableRow >
           <TableCell>
                    <IconButton aria-label="expand row" size="small" onClick={handleSetOpen} >
                    {open ? <KeyboardArrowUpIcon/> : <KeyboardArrowDownIcon/>}
                    </IconButton>
            </TableCell>
            <TableCell align='center' component="th" scope="row" >{row.id}</TableCell>
            <TableCell align='center'>{row.name}</TableCell>
            <TableCell align='center'>{row.price}</TableCell>
            <TableCell align="center"><ActiveIcon active={row.lights}/></TableCell>
            <TableCell align="center">{row.sector}</TableCell>
            <TableCell align="center">{row.min_people}</TableCell>
            <TableCell align="center">{row.max_people}</TableCell>
            <TableCell align="center">{row.grass_type}</TableCell>
            <TableCell align="center"><ActiveIcon active={row.goal_nets}/></TableCell>
            <TableCell align="center"><ActiveIcon active={row.rented}/></TableCell>
            <TableCell align="center"><BaseButton name='Rent' onClick={()=>{
              handleSetRent().then(res=>{
                onChange().then(()=>{
                  console.log("succes!")
                })
              }) 
            }}enable={row.rented} /> </TableCell>
    </TableRow>
        <TableRow>
        <TableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={6}>
            <Collapse in={open} timeout="auto" unmountOnExit>
                <Box margin={1}>
                    <Table size="small" aria-label="clients">
                        <TableHead>

                        </TableHead>
                        <TableBody>
                            <TableRow>
                                <TableCell align="center">
                                    <BaseButton name="test1"/>
                                    <BaseButton name="Test2"/>
                                </TableCell>
                            </TableRow>
                            </TableBody>
                            </Table>
                        </Box>
                    </Collapse>
                </TableCell>
            </TableRow>
                            </React.Fragment>
    );
}
Row.propTypes ={
    optionalObjectWithShape: PropTypes.shape({
        id: PropTypes.string,
        name: PropTypes.string,
        price: PropTypes.number,
        lights: PropTypes.bool,
        sector: PropTypes.bool,
        min_people: PropTypes.number,
        max_people: PropTypes.number
      }),
}
function getPitches(){
    return axios.get(`Pitches/footballPitches/`)
    
}
function BasicTable() {
    const [pitches, setPitches] = useState([]);
    const getUpdatedPiches =()=>{
      return getPitches().then(res=>{
        setPitches(res.data)
      })
    }
    useEffect(() => {
        getUpdatedPiches()
    }, []);
    return (
      <TableContainer component={Paper} className={styles.table}>
        <Table  aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell />
              <TableCell align="center">id</TableCell>
              <TableCell align="center">name</TableCell>
              <TableCell align="center">price</TableCell>
              <TableCell align="center">lights</TableCell>
              <TableCell align="center">sector</TableCell>
              <TableCell align="center">min_people</TableCell>
              <TableCell align="center">max_people</TableCell>
              <TableCell align="center">grass_type</TableCell>
              <TableCell align="center">goal_nets</TableCell>
              <TableCell align="center">rented</TableCell>
             
            </TableRow>
          </TableHead>
          <TableBody>
            {pitches.map((row, index ) => (
              <Row key={index} row={row} onChange={getUpdatedPiches}/>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
  }
function footballPitch() {
    return (
        <Box >
            <Header title="Rent a football Pitch"/>
            <Box style={{
                height: '100vh',
             
            }}>
                <h1 className={styles.title}>Footbal Pitch page</h1>
                <BasicTable/>
                </Box>
            <Footer />
        </Box>
    )
}

export default footballPitch
