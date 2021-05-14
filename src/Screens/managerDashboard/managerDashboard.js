import React, {useEffect, useState} from "react";
import './managerScreen.css'
import {Button, CircularProgress, Input, Link} from "@material-ui/core";
import Logo from "../../LoginScreen/georgeLogo150-150.png";
import BackGround from "../agentDashboard/Images/agentScreenback.jpeg";
import {Link as RouteLink} from 'react-router-dom';
import ProductsList from "./productsList";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogActions from "@material-ui/core/DialogActions";
import Dialog from "@material-ui/core/Dialog";
import axios from "axios";
import Slide from "@material-ui/core/Slide";
const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});
//import IntroductionSlider from "./introductionSlider";
const ManagerDashboard = () =>{
    const User=JSON.parse(localStorage.getItem("user"));
    const [open, setOpen] = React.useState(false);
    const [loading,setLoading]=useState(false);
    const [numeProdus,setNumeProdus]=useState('');
    const [descriereProdus,setDescriereProdus]=useState('');
    const [pret,setPret]=useState(0.0);
    const [cantitate,setCantitate]=useState(0);
    const [products,setProducts]=useState([]);
    const adaugaURL='http://localhost:8080/firma/operations/addProduct/'+User.firmaID;
    const productsURL='http://localhost:8080/firma/operations/products/'+User.firmaID;
    const logout= () =>{
        localStorage.removeItem("user");
    }
    useEffect(()=>{
        getProducts();
        console.log(products);
    },[])
    const getProducts = async () =>{
        setLoading(true)
        await axios.get(productsURL)
            .then(function (response) {
                let initialData=[];
                for(let i=0;i<response.data.length;i++){
                    initialData.push(response.data[i]);
                }
                setLoading(false);
                setProducts(initialData);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    const handleAdd = async ()=>{
        setLoading(true);
        await axios.post(adaugaURL,{
            numeProdus:numeProdus,
            descriereProdus:descriereProdus,
            pret:pret,
            cant:cantitate,
        }).then(function (response) {
            console.log(response)
            setLoading(false);
            getProducts();
            setOpen(false);
        })
            .catch(function (error) {
                console.log(error);
            });
    }
    const onChangeNume = (e)=>{
        setNumeProdus(e.target.value);
    }
    const onChangeDescriere = (e)=>{
        setDescriereProdus(e.target.value);
    }
    const onChangePret=(e)=>{
        setPret(e.target.value);
    }
    const onChangeCantitate = (e)=>{
        setCantitate(e.target.value);
    }

    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    return(
        <div className="bodyAgentScreen">
            <header className="agentScreen">
                <div className="logoAgentScreen">
                    <img src={Logo} alt="Logo"/>
                </div>
                <div className="navigation">
                    <Button variant="contained">
                        <RouteLink className="link" to="/managerDashboard"><p>Home</p></RouteLink>
                    </Button>
                    <Button variant="contained">
                        <RouteLink className="link" to="/comenziPrimite"> <p>Vizualizare comenzi primite</p></RouteLink>
                    </Button>

                    <Button variant="contained">
                        <RouteLink onClick={logout} className="link" to="/"><p>Log out</p></RouteLink>
                    </Button>
                </div>
            </header>
            <Dialog
                open={open}
                TransitionComponent={Transition}
                keepMounted
                onClose={handleClose}
                aria-labelledby="alert-dialog-slide-title"
                aria-describedby="alert-dialog-slide-description"
            >
                <DialogTitle id="alert-dialog-slide-title">{"Confirma adauga produs"}</DialogTitle>
                <DialogContent className="dialogcontent">
                    <DialogContentText>Nume produs</DialogContentText>
                    {loading && <CircularProgress/>}
                    {!loading &&<Input
                        type={'text'}
                        fullWidth
                        value={numeProdus}
                        onChange={onChangeNume}
                        placeholder="ex. Oglinda"
                    />}
                    <DialogContentText>Descriere produs produs</DialogContentText>
                    {loading && <CircularProgress/>}
                    {!loading &&<Input
                        type={'text'}
                        fullWidth
                        onChange={onChangeDescriere}
                        value={descriereProdus}
                        placeholder="ex. Oglinda pentru Mercedes"
                    />}
                    <DialogContentText>Pret produs</DialogContentText>
                    {loading && <CircularProgress/>}
                    {!loading &&<Input
                        type={'text'}
                        fullWidth
                        value={pret}
                        onChange={onChangePret}
                        placeholder="ex. 50.2"
                    />}
                    <DialogContentText>Cantitate</DialogContentText>
                    {loading && <CircularProgress/>}
                    {!loading &&<Input
                        type={'text'}
                        fullWidth
                        onChange={onChangeCantitate}
                        value={cantitate}
                        placeholder="ex. 5"
                    />}
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleAdd} color="primary">
                        Adauga produs
                    </Button>
                </DialogActions>
            </Dialog>
            <div className="mainContent">
                <img src={BackGround}/>
                <div className="introduction">
                    <div className="introductionText">
                        <h1>Produsele companiei </h1>
                        <Button onClick={handleClickOpen} variant="contained">
                             <p>Adauga produs</p>
                        </Button>
                    </div>
                    <div>
                        <h1>Products list</h1>
                        <ProductsList products={products} refresh={getProducts}/>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default ManagerDashboard;