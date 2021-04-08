import React, {useEffect} from "react";
import './agentScreen.css'
import {Button, Link} from "@material-ui/core";
import Logo from "../../LoginScreen/georgeLogo150-150.png";
import BackGround from "../Images/agentScreenback.jpeg";
import {Link as RouteLink} from 'react-router-dom';
import IntroductionSlider from "./introductionSlider";
const AgentScreen = () =>{

    return(
        <div className="bodyAgentScreen">
            <header className="agentScreen">
                <div className="logoAgentScreen">
                    <img src={Logo} alt="Logo"/>
                </div>
                <div className="navigation">
                    <Button variant="contained">
                        <RouteLink className="link" to="/agentDashboard"><p> Home</p></RouteLink>
                    </Button>
                    <Button variant="contained">
                       <RouteLink className="link" to="/products"> <p>Produse</p></RouteLink>
                    </Button>
                    <Button variant="contained">
                        <p>Vizualizare istoric comenzi</p>
                    </Button>
                    <Button variant="contained">
                        <RouteLink className="link" to="/"><p>Log out</p></RouteLink>
                    </Button>
                </div>
            </header>
            <div className="mainContent">
                <img src={BackGround}/>
                <div className="introduction">
                    <div className="introductionText">
                       <h1> Cea mai buna companie de produse auto.</h1>
                    </div>
                    <IntroductionSlider/>
                </div>
            </div>
        </div>
    )
}

export default AgentScreen;