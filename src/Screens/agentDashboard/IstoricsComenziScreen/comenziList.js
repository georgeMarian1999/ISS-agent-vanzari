import React, {useEffect, useState} from "react";
import Grid from '@material-ui/core/Grid';
import './productsScreen.css';
import {CircularProgress} from "@material-ui/core";
import Comanda from './Comanda';
import axios from "axios";
const ComenziList = ()=>{
    const User=JSON.parse(localStorage.getItem("user"));
    const [comenzi,setComenzi]=useState([]);
    const [loading,setLoading]=useState(false);
    const productsURL='http://localhost:8080/firma/operations/history/'+User.userID;

    useEffect(()=>{
        getComenzi();
    },[])
    const getComenzi = async () =>{
        setLoading(true)
        await axios.get(productsURL)
            .then(function (response) {
                let initialData=[];
                for(let i=0;i<response.data.length;i++){
                    initialData.push(response.data[i]);
                }
                console.log(initialData);
                setLoading(false);
                setComenzi(initialData);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    const anulareComanda= async (comandaID) =>{
        const anulareURL='http://localhost:8080/firma/operations/anulareComanda/'+comandaID;
        await axios.put(anulareURL)
            .then(function (response) {
                getComenzi();
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    const returnareComanda = async (comandaID)=>{
        const returnareURL='http://localhost:8080/firma/operations/returnare/'+comandaID;
        await axios.put(returnareURL)
            .then(function (response) {
                getComenzi();
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    return(
        <div>
            {loading && <CircularProgress/>}
            {!loading &&<div style={{textAlign: 'center'}}>
            <h1>Istoric comenzi efectuate</h1>
            <Grid container justify="center" spacing={2}>
                {comenzi.map((comanda) => (
                    <Grid key={comanda.comandaID} item>
                        <Comanda comanda={comanda} anulare={anulareComanda} returnare={returnareComanda} loading={loading} />
                    </Grid>
                ))}
            </Grid>
            </div>}
        </div>
    )
}


export default ComenziList;