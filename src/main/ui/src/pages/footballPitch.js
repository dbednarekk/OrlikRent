import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Header from "../components/Header";
import Footer from "../components/Footer";
import styles from "../styles/FootballPitch.module.css";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import axios from "../Services/URL";
import ActiveIcon from "../components/ActiveIcon";
import IconButton from "@mui/material/IconButton";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import Collapse from "@mui/material/Collapse";
import BaseButton from "../components/BaseButton";
import Autocomplete from "../components/Autocomplete";
import TextField from "@mui/material/TextField";
import useErrorHandler from "../errorHandler.ts";
import useSnackbarQueue from "../components/Snackbar.ts";
import {Link} from "react-router-dom";
import PopupData from "../pages/PopupRent.tsx"

function SubRentRow(subprops) {
  const { subrow } = subprops;
  return (
    <React.Fragment>
      <TableRow>
        <TableCell align="center">{subrow.accountID}</TableCell>
        <TableCell align="center">{subrow.start_date_rental}</TableCell>
        <TableCell align="center">{subrow.end_date_rental}</TableCell>
        <TableCell align="center">
          <ActiveIcon active={subrow.active} />
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}

function Row(props) {
  const { row } = props;
  const token = sessionStorage.getItem('JWTToken') ;
  const handleError = useErrorHandler()
  const showSuccess = useSnackbarQueue('success')
  const { onChange } = props;
  const [open, setOpen] = React.useState(false);
  const [, setRent] = React.useState(false);
  const [rentForPitch, setRentForPitch] = React.useState([]);
  const [openPopup, setOpenPopup] = useState(false);

  const handleSetOpen = async () => {
    setOpen((state) => !state);
    axios.get(`/Rentals/RentsForPitch/${row.id}`,{
      headers:{
        'Authorization': `Bearer ${token}`
      }
    }).then((res) => {
      setRentForPitch(res.data);
    }).catch(error =>{
      console.log(error.response.data)
      const message = error.response.data
      handleError(message, error.response.status)
    });
  };

 
  return (
    <React.Fragment>
      <TableRow>
        <TableCell>
          <IconButton
            aria-label="expand row"
            size="small"
            onClick={handleSetOpen}
          >
            {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
          </IconButton>
        </TableCell>
        <TableCell align="center" component="th" scope="row">
          {row.id}
        </TableCell>
        <TableCell align="center">{row.name}</TableCell>
        <TableCell align="center">{row.price}</TableCell>
        <TableCell align="center">
          <ActiveIcon active={row.lights} />
        </TableCell>
        <TableCell align="center">{row.sector}</TableCell>
        <TableCell align="center">{row.min_people}</TableCell>
        <TableCell align="center">{row.max_people}</TableCell>
        <TableCell align="center">{row.grass_type}</TableCell>
        <TableCell align="center">
          <ActiveIcon active={row.goal_nets} />
        </TableCell>
        <TableCell align="center">
          <ActiveIcon active={row.rented} />
        </TableCell>
        <TableCell align="center">
        <BaseButton
          enable={false}
          name="Rent"
          onClick={() => setOpenPopup(true)}
          
        />
        <PopupData
          open={openPopup}
          onCancel={() => onChange().then(res =>{
            setOpenPopup(false)
          })}                         
          id={row.id}
          pitch={row}
        />
        </TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box margin={1}>
              <Table size="small" aria-label="clients">
                <TableHead></TableHead>
                <TableBody>
                  <TableContainer component={Paper} className={styles.table}>
                    <Table aria-label="Rent table">
                      <TableHead>
                        <TableRow>
                          <TableCell align="center">Account ID</TableCell>
                          <TableCell align="center">Start Date</TableCell>
                          <TableCell align="center">End Date</TableCell>
                          <TableCell align="center">Active</TableCell>
                        </TableRow>
                      </TableHead>
                      <TableBody>
                        {rentForPitch.map((subrow, indexx) => (
                          <SubRentRow key={indexx} subrow={subrow} />
                        ))}
                      </TableBody>
                    </Table>
                  </TableContainer>
                </TableBody>
              </Table>
            </Box>
          </Collapse>
        </TableCell>
      </TableRow>
    </React.Fragment>
  );
}
function getPitches() {
  const token = sessionStorage.getItem("JWTToken")
  return axios.get(`Pitches/footballPitches/`,{
    headers:{
      'Authorization': `Bearer ${token}`
    }});
}
function BasicTable() {
  const [pitches, setPitches] = useState([]);
  const handleError = useErrorHandler()
  const getUpdatedPiches = () => {
    return getPitches().then((res) => {
      setPitches(res.data);
    });
  };
  useEffect(() => {
    getUpdatedPiches().catch(error =>{
      const message = error.response.data
      handleError(message, error.response.status)
    });
  }, []);
  const accounts = [];
  const [searchInput, setSearchInput] = useState("");

  function search(rows) {
    if (Array.isArray(rows) && rows.length) {
      const filteredAccount = rows.filter(
        (row) =>
          row.props.row.id.toLowerCase().indexOf(searchInput.toLowerCase()) > -1
      );

      filteredAccount.forEach((account) =>
        accounts.includes(account.props.row.id)
          ? ""
          : accounts.push(account.props.row.id)
      );
      return filteredAccount;
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
          options={accounts}
          inputValue={searchInput}
          noOptionsText="no options"
          onChange={(event, value) => {
            setSearchInput(value ? value : "");
          }}
          renderInput={(params) => (
            <TextField
              {...params}
              label="search pitch"
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
            {search(
              pitches.map((row, index) => (
                <Row key={index} row={row} onChange={getUpdatedPiches} />
              ))
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
}
function footballPitch() {
  return (
    <Box>
      <Header title="Rent a football Pitch" />
      <Box
        style={{
          height: "100vh",
        }}
      >
        <h1 className={styles.title}>Footbal Pitch page</h1>
        <BasicTable />
      </Box>
      <Footer />
    </Box>
  );
}

export default footballPitch;
