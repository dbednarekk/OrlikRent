import React from "react";
import "./styles/App.css";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import FootballPitch from "./pages/footballPitch";
import BasketballPitch from "./pages/basketballPitch";
import AdminPage from "./pages/admin/AdminPage";
import ModeratorPage from "./pages/moderator/ModeratorPage";
import AddFPitch from "./pages/addFPitch";
import AddBPitch from "./pages/addBPitch";
import AddAccount from "./pages/addAccount";
import EditAccount from "./pages/editAccount";
import Login from "./pages/Login";
import EditPitch from "./pages/editPitch";
import EditRent from "./pages/editRent";
import { Switch } from "@mui/material";
import MyProfile from "./pages/client/MyProfile";
import Register from "./pages/Register"
import img404 from "./images/404.png"
function App() {

  return (
    <Router basename="OrlikRentPAS/">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="footballPitch/" element={<FootballPitch />} />
        <Route path="basketballPitch/" element={<BasketballPitch />} />
        <Route path="addFPitch/" element={<AddFPitch />} />
        <Route path="addBPitch/" element={<AddBPitch />} />
        <Route path="addAccount/" element={<AddAccount />} />
        <Route path="Admin//*" element={<AdminPage />} />
        <Route path="Manager//*" element={<ModeratorPage />} />
        <Route path="editAccounts/" element={<EditAccount />} />
        <Route path="login//*" element={<Login />} />
        <Route path="register//*" element={<Register />} />
        <Route path="editPitch/" element={<EditPitch />} />
        <Route path="editRent/" element={<EditRent />} />
        <Route path="myProfile//*" element={<MyProfile />} />
        <Route path="*" element={ <div style={{
                                display: 'flex',
                                flexDirection: 'column',
                                justifyContent: 'center',
                                alignItems: 'center',
                                width: '100%',
                                height: '100vh',
                            
                            }}> <img src={img404} alt="404 not found"/></div>} />
      </Routes>
    </Router>
  );
}

export default App;
