import React from "react";
import Box from "@mui/material/Box";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import PanelLayout from "../../components/PanelLayout";
import AccountsListIcon from "@mui/icons-material/PeopleAlt";
import ListClientRentals from "./ListClientRentals";
import ResetPassword from "./ResetPassword";
function ModeratorPage() {
    const login = JSON.parse(sessionStorage.getItem("Login"))
  const auth = JSON.parse(sessionStorage.getItem("Auth"))
  return (
    <Box>
      <Header title={login + " Profile"}/>
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
                link: "MyRentals/",
                Icon: AccountsListIcon,
                text: "My Rentals",
                Component: ListClientRentals,
              },
              {
                link: "ChangePassword/",
                Icon: AccountsListIcon,
                text: "Change Password",
                Component: ResetPassword,
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
