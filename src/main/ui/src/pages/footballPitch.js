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
  function createData(id, name, price, lights, sector, min_people, max_people, rented) {
    return { id, name, price, lights, sector, min_people, max_people, rented};
  }

function Row (props) {
    const {row} = props;
    return (
        <TableRow >
            <TableCell component="th" scope="row" >{row.id}</TableCell>
            <TableCell >{row.name}</TableCell>
            <TableCell >{row.price}</TableCell>
            <TableCell >{row.lights}</TableCell>
            <TableCell >{row.sector}</TableCell>
            <TableCell >{row.min_people}</TableCell>
            <TableCell >{row.max_people}</TableCell>
            <TableCell >{row.rented}</TableCell>
        </TableRow>
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
const getPitches = () =>{
    return axios.get(`Pitches/footballPitches/`)
}
function BasicTable() {
    const [pitches, setPitches] = useState([]);
    useEffect(() => {
        getPitches().then(res => {
            setPitches(res.data) 
        })
    }, []);
    return (
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>id</TableCell>
              <TableCell align="right">name</TableCell>
              <TableCell align="right">price</TableCell>
              <TableCell align="right">lights</TableCell>
              <TableCell align="right">sector</TableCell>
              <TableCell align="right">min_people</TableCell>
              <TableCell align="right">max_people</TableCell>
              <TableCell align="right">rented</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {pitches.map((row) => (
              <TableRow
                key={row.id}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {row.id}
                </TableCell>
                <TableCell align="right">{row.name}</TableCell>
                <TableCell align="right">{row.price}</TableCell>
                <TableCell align="right"><ActiveIcon active={row.lights}/></TableCell>
                <TableCell align="right">{row.sector}</TableCell>
                <TableCell align="right">{row.min_people}</TableCell>
                <TableCell align="right">{row.max_people}</TableCell>
                <TableCell align="right"><ActiveIcon active={row.lights}/></TableCell>
              </TableRow>
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
                <h1 className={styles.title}>Footbal Pitch page</h1></Box>
                <BasicTable/>
            <Footer />
        </Box>
    )
}

export default footballPitch
