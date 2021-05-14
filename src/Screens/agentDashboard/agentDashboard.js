import React, {useEffect} from "react";
import './agentScreen.css'
import {Button, Link} from "@material-ui/core";
import Logo from "../../LoginScreen/georgeLogo150-150.png";
import BackGround from "./Images/agentScreenback.jpeg";
import {Link as RouteLink} from 'react-router-dom';
import ProductsScreen from "./productsScreen/productsScreen";
//import IntroductionSlider from "./introductionSlider";
const AgentDashboard = () =>{
    const logout= () =>{
        localStorage.removeItem("user");
    }
    return(
        <div>
            <ProductsScreen/>
        </div>
    )
}

export default AgentDashboard;