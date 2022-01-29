import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import styles from "../../styles/FootballPitch.module.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import axios from "../../Services/URL";
import ActiveIcon from "../../components/ActiveIcon";
import BaseButton from "../../components/BaseButton";
import Autocomplete from "../../components/Autocomplete";
import TextField from "@mui/material/TextField";
import useErrorHandler from "../../errorHandler.ts";
import useSnackbarQueue from "../../components/Snackbar.ts";


function Row(props) {
  const { row } = props;
  const token = sessionStorage.getItem('JWTToken') ;
  const handleError = useErrorHandler()
  const showSuccess = useSnackbarQueue('success')
  const { onChange } = props;
  const handleEndReservation =()=>{
      axios.patch(`Rentals/endRental/${row.id}`,{},{
        headers:{
          'Authorization': `Bearer ${token}`
        }
      }).then(()=>{
        onChange()
        showSuccess("Successful action")
      }).catch(error =>{
      
        const message = error.response.data
        handleError(message, error.response.status)
      });
  }

 
  return (
    <React.Fragment>
      <TableRow>
        <TableCell/>
        <TableCell align="center" component="th" scope="row">
          {row.id}
        </TableCell>
        <TableCell align="center">{row.pitchID}</TableCell>
        <TableCell align="center">{row.start_date_rental}</TableCell>
        <TableCell align="center">{row.end_date_rental}</TableCell>
        <TableCell align="center">
          <ActiveIcon active={row.active} />
        </TableCell>
        <TableCell align="center">
        <BaseButton
          enable={!row.active}
          name="End Rent"
          onClick={handleEndReservation}
          
        />
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}

function BasicTable() {
  const [rentals, setRentals] = useState([]);
  const handleError = useErrorHandler()
  const login = JSON.parse(sessionStorage.getItem("Login"))
  const getRentals = () => {
    const token = sessionStorage.getItem("JWTToken")
    return axios.get(`Rentals/RentsForClient/${login}`,{
      headers:{
        'Authorization': `Bearer ${token}`
      }
    })
  }

  const getUpdatedRentals = () => {
    return getRentals().then((res) => {
      setRentals(res.data);
    }).catch(error =>{
      console.log(error.response.data)
      const message = error.response.data
      handleError(message, error.response.status)
    });
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
      <div>
        <Autocomplete
          options={rnts}
          inputValue={searchInput}
          noOptionsText="no options"
          onChange={(event, value) => {
            setSearchInput(value ? value : "");
          }}
          renderInput={(params) => (
            <TextField
              {...params}
              label="search Rents"
              variant="outlined"
              onChange={(e) => setSearchInput(e.target.value)}
            />
          )}
        />
      </div>
      <TableContainer component={Paper} className={styles.table}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell />
              <TableCell align="center">id</TableCell>
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
function ListClientRentals() {
  return (
    <div>
      <BasicTable />
    </div>
  );
}

export default ListClientRentals;
