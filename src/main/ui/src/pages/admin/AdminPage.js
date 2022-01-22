import React from "react";
import Box from "@mui/material/Box";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import PanelLayout from "../../components/PanelLayout";
import AccountsListIcon from "@mui/icons-material/PeopleAlt";
import ListAccounts from "./ListAccounts";
import ListPitches from "./ListPitches";
import ListRentals from "./ListRentals";

function AdminPage() {
  return (
    <Box>
      <Header title="Admin panel" />
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
                link: "listAccounts/",
                Icon: AccountsListIcon,
                text: "list accounts",
                Component: ListAccounts,
              },
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
              },
            ]}
          ></PanelLayout>
        </Box>
      </Box>
      <Footer />
    </Box>
  );
}

export default AdminPage;
