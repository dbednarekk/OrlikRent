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
import IconButton from "@mui/material/IconButton";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import Collapse from "@mui/material/Collapse";
import BaseButton from "../../components/BaseButton";
import Autocomplete from "../../components/Autocomplete";
import TextField from "@mui/material/TextField";
import useErrorHandler from "../../errorHandler";
function Row(props) {
  const { row } = props;
  const [open, setOpen] = React.useState(false);

  const handleSetOpen = async () => {
    setOpen((state) => !state);
  };
  const handleRemove = () => {
    console.log("handle Remove");
  };
  const handleEdit = () => {
    console.log("handle Edit");
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
        <TableCell align="center">{row.accountID}</TableCell>
        <TableCell align="center">{row.pitchID}</TableCell>
        <TableCell align="center">{row.start_date_rental}</TableCell>
        <TableCell align="center">{row.end_date_rental}</TableCell>
        <TableCell align="center">
          <ActiveIcon active={row.active} />
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
                    <Table aria-label="pitch table">
                      <TableHead></TableHead>
                      <TableBody
                        style={{
                          display: "flex",

                          alignItems: "center",
                        }}
                      >
                        <BaseButton
                          enable={false}
                          name="Remove"
                          onClick={handleRemove}
                        />
                        <BaseButton
                          enable={false}
                          name="Edit"
                          onClick={handleEdit}
                        />
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
function getRentals() {
  return axios.get(`Rentals/`);
}
function BasicTable() {
  const [rentals, setRentals] = useState([]);
  const handleError = useErrorHandler()
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
          row.props.row.id.toLowerCase().indexOf(searchInput.toLowerCase()) > -1
      );

      filteredRent.forEach((rent) =>
        rnts.includes(rent.props.row.id) ? "" : rnts.push(rent.props.row.id)
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


function ListRentals() {
  return (
    <div>
      <BasicTable />
    </div>
  );
}

export default ListRentals;