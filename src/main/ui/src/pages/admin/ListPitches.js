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
import {Link} from "react-router-dom";

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
  const handleViewDetails = () => {
    console.log("handle view details");
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
        <TableCell align="center">{row.sector}</TableCell>
        <TableCell align="center">
          <ActiveIcon active={row.rented} />
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
                        <BaseButton
                          enable={false}
                          name="Details"
                          onClick={handleViewDetails}
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
function getPitches() {
  return axios.get(`Pitches/`);
}
function BasicTable() {
  const [pitches, setPitches] = useState([]);
  const handleError = useErrorHandler()
  const getUpdatedPiches = () => {
    return getPitches().then((res) => {
      setPitches(res.data);
    }).catch(error =>{
      console.log(error.response.data)
      const message = error.response.data
      handleError(message, error.response.status)
    });
  };
  useEffect(() => {
    getUpdatedPiches();
  }, []);
  const pitchs = [];
  const [searchInput, setSearchInput] = useState("");

  function search(rows) {
    if (Array.isArray(rows) && rows.length) {
      const filteredpitch = rows.filter(
        (row) =>
          row.props.row.id.toLowerCase().indexOf(searchInput.toLowerCase()) > -1
      );

      filteredpitch.forEach((pitch) =>
        pitchs.includes(pitch.props.row.id)
          ? ""
          : pitchs.push(pitch.props.row.id)
      );
      return filteredpitch;
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
          options={pitchs}
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
              <TableCell align="center">sector</TableCell>
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


function ListPitches() {
  return (
    <div>
      <Link to="/addFPitch/">
        <BaseButton enable={false} name="Add Football Pitch" />
      </Link>
      <Link to="/addBPitch/">
        <BaseButton enable={false} name="Add Basketball Pitch"  />
      </Link>
      <BasicTable />
    </div>
  );
}

export default ListPitches;
