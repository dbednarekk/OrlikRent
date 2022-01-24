import React from "react"
import './styles/App.css';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './pages/Home'
import FootballPitch from "./pages/footballPitch";
import BasketballPitch from "./pages/basketballPitch";
import AdminPage from "./pages/admin/AdminPage";
import ModeratorPage from "./pages/moderator/ModeratorPage"
import AddFPitch from "./pages/addFPitch";
import AddBPitch from "./pages/addBPitch";

function App() {

  return (
    <Router basename='OrlikRentPAS/'>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="footballPitch/" element={<FootballPitch/>}/>
        <Route path="basketballPitch/" element={<BasketballPitch/>} />
        <Route path="addFPitch/" element={<AddFPitch/>}/>
        <Route path="addBPitch/" element={<AddBPitch/>} />
        <Route path="admin//*" element={<AdminPage/>} />
        <Route path="moderator//*" element={<ModeratorPage/>} />
      </Routes>
    </Router>

  );
}

export default App;
