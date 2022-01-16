import React from 'react'
import Footer from '../components/Footer'
import PitchCard from '../components/ActionAreaCard'
import footballPitch from '../images/footballPitch.jpg'
import basketballPitch from '../images/basketballPitch.jpg'
import Box from '@mui/material/Box'
import {Link} from "react-router-dom";
import Header from '../components/Header';
function Home() {
    return (
        <div>
             <Header title="Welcome to Pitch rental!"></Header>
      <Box  sx={{
         display: 'flex',
         justifyContent: 'space-around',
         alignItems: 'center',
         width: '70%',
         position: 'fixed',
         flexDirection: 'row',
         top: '15%',
         left: '15%',
       
      }}>
        <Link to="/footballPitch/">
      <PitchCard image={footballPitch} title="Football Pitch" description="Choose from various football pitches!"style={{
        boxShadow: '10px 10px 5px #aaaaaa'
      }} ></PitchCard>
      </Link>
      <Link to="/basketballPitch/">
      <PitchCard image={basketballPitch} title="Basketball Pitch" description="Choose from various basketball pitches!" ></PitchCard>
      </Link>
      </Box>
      <Footer></Footer>
        </div>
    )
}

export default Home
