import React from "react";
import Logo from "../../LoginScreen/georgeLogo150-150.png";
import {Button} from "@material-ui/core";
import {Link as RouteLink} from "react-router-dom";
import './productsScreen.css';
import BackGround from "../Images/carssketch.jpeg";
import ProductsList from "./productsList";

const ProductsScreen = () =>{
    return(
        <div className="bodyProductsScreen">
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
            <div className="allProducts">
                <img src={BackGround}/>
                <div className="list">
                    <ProductsList/>
                </div>
            </div>
        </div>
    )
}

export default ProductsScreen;