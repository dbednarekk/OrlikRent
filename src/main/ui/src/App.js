import React from "react"
import './styles/App.css';
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './pages/Home'
import FootballPitch from "./pages/footballPitch";
import BasketballPitch from "./pages/basketballPitch";
function App() {
  return (
    <Router basename='OrlikRentPAS/'>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="footballPitch/" element={<FootballPitch/>}/>
        <Route path="basketballPitch/" element={<BasketballPitch/>} />
      </Routes>
    </Router>

  );
}

export default App;
