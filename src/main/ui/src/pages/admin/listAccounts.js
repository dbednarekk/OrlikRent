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

  const handleBlock = () => {
    console.log("handle block");
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
        <TableCell align="center">{row.login}</TableCell>
        <TableCell align="center">{row.email}</TableCell>
        <TableCell align="center">
          <ActiveIcon active={row.active} />
        </TableCell>
        <TableCell align="center">{row.role}</TableCell>
      </TableRow>
      <TableRow>
        <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
          <Collapse in={open} timeout="auto" unmountOnExit>
            <Box margin={1}>
              <Table size="small" aria-label="clients">
                <TableHead></TableHead>
                <TableBody>
                  <TableContainer component={Paper} className={styles.table}>
                    <Table aria-label="account table">
                      <TableHead></TableHead>
                      <TableBody
                        style={{
                          display: "flex",

                          alignItems: "center",
                        }}
                      >
                        <BaseButton
                          enable={false}
                          name={row.active ? "deactive" : "active"}
                          onClick={handleBlock}
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
function getAccounts() {
  return axios.get(`Account/`);
}
function BasicTable() {
  const [accounts, setAccounts] = useState([]);
  const handleError = useErrorHandler()
  const getUpdatedAccounts = () => {
    return getAccounts().then((res) => {
      setAccounts(res.data);
    }).catch(error =>{
      console.log(error.response.data)
      const message = error.response.data
      handleError(message, error.response.status)
    });
  };
  useEffect(() => {
    getUpdatedAccounts();
  }, []);
  const fAccounts = [];
  const [searchInput, setSearchInput] = useState("");

  function search(rows) {
    if (Array.isArray(rows) && rows.length) {
      const filteredAccount = rows.filter(
        (row) =>
          row.props.row.id.toLowerCase().indexOf(searchInput.toLowerCase()) > -1
      );

      filteredAccount.forEach((account) =>
        fAccounts.includes(account.props.row.id)
          ? ""
          : fAccounts.push(account.props.row.id)
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
          options={fAccounts}
          inputValue={searchInput}
          noOptionsText="no options"
          onChange={(event, value) => {
            setSearchInput(value ? value : "");
          }}
          renderInput={(params) => (
            <TextField
              {...params}
              label="search account"
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
              <TableCell align="center">login</TableCell>
              <TableCell align="center">email</TableCell>
              <TableCell align="center">active</TableCell>
              <TableCell align="center">role</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {search(
              accounts.map((row, index) => (
                <Row key={index} row={row} onChange={getUpdatedAccounts} />
              ))
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
}
const handleAdd = () => {
  console.log("Handle add");
};
function ListAccounts() {
  return (
    <div>
      <BaseButton enable={false} name="Add Account" onClick={handleAdd} />
      <BasicTable />
    </div>
  );
}

export default ListAccounts;