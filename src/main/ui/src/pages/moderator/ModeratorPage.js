import React from "react";
import Box from "@mui/material/Box";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import PanelLayout from "../../components/PanelLayout";
import AccountsListIcon from "@mui/icons-material/PeopleAlt";
import ListPitches from "./ListPitches";
import ListRentals from "./ListRentals";
import {Navigate} from "react-router-dom";
function ModeratorPage() {
  const auth = JSON.parse(sessionStorage.getItem("Auth"))
  if(!(auth==="Manager")){
    return <Navigate to='/'></Navigate>
  }
  return (
    <Box>
      <Header title="Moderator panel" />
      <Box
        style={{
          height: "100vh",
        }}
      >
        <Box
          style={{
            position: "relative",
            top: "20%",
          }}
        >
          <PanelLayout
            menu={[
              {
                link: "listPitches/",
                Icon: AccountsListIcon,
                text: "list Pitches",
                Component: ListPitches,
              },
              {
                link: "listRentals/",
                Icon: AccountsListIcon,
                text: "list Rentals",
                Component: ListRentals,
              }
            ]}
          ></PanelLayout>
        </Box>
      </Box>
      <Footer />
    </Box>
  );
}

export default ModeratorPage;
