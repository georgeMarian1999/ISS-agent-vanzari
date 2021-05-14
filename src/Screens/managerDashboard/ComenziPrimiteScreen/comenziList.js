import React, {useEffect, useState} from "react";
import Grid from '@material-ui/core/Grid';
import './productsScreen.css';
import {CircularProgress} from "@material-ui/core";
import Comanda from './Comanda';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import axios from "axios";
const ComenziList = ()=>{
    const User=JSON.parse(localStorage.getItem("user"));
    const [comenzi,setComenzi]=useState([]);
    const [loading,setLoading]=useState(false);
    const [type,setType]=useState('Procesare');
    const productsURL='http://localhost:8080/firma/operations/ordersreceived/'+User.firmaID+"/"+type;
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
    const handleChange = (e)=>{
        setType(e.target.value);

    }
    useEffect(()=>{
        console.log(type);
        getComenzi();
    },[type])
    const ConfirmareComanda= async (comandaID) =>{
        const anulareURL='http://localhost:8080/firma/operations/confirmareComanda/'+comandaID;
        await axios.put(anulareURL)
            .then(function (response) {
                getComenzi();
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    const ConfirmareRetur = async (comandaID)=>{
        const returnareURL='http://localhost:8080/firma/operations/confirmareRetur/'+comandaID;
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
                <div className="selectForm">
            <h1>Comenzi primite de catre firma</h1>

                </div>
                <InputLabel>
                    Statusul comenzii
                </InputLabel>
                <FormControl >

                    <Select
                        labelId="demo-simple-select-placeholder-label-label"
                        value={type}
                        style={{padding: 10}}
                        onChange={handleChange}
                        displayEmpty
                    >
                        <MenuItem value="">
                            <em>None</em>
                        </MenuItem>
                        <MenuItem value="Procesare">Procesare</MenuItem>
                        <MenuItem value="Confirmata">Confirmata</MenuItem>
                        <MenuItem value="Returnare">Returnare</MenuItem>
                        <MenuItem value="Anulata">Anulata</MenuItem>
                    </Select>
                </FormControl>
            <Grid container justify="center" spacing={2}>
                {comenzi.map((comanda) => (
                    <Grid key={comanda.comandaID} item>
                        <Comanda comanda={comanda} confirmare={ConfirmareComanda} retur={ConfirmareRetur} loading={loading} />
                    </Grid>
                ))}
            </Grid>
            </div>}
        </div>
    )
}


export default ComenziList;