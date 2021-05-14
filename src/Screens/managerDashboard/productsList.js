import React, {useEffect, useState} from "react";
import Grid from '@material-ui/core/Grid';
import Product from "./product";
import {CircularProgress} from "@material-ui/core";
import axios from "axios";
const ProductsList = (props)=>{
    const User=JSON.parse(localStorage.getItem("user"));

    const [loading,setLoading]=useState(false);

    const adaugaStocURL='http://localhost:8080/firma/operations/addStoc';

    const adaugaStoc = async (produsID,cantitate)=>{
        await axios.post(adaugaStocURL,{
                produsID: produsID,
                cant: cantitate,
            }
        ).then(function (response) {
            console.log(response);
            setTimeout(() => {
                props.refresh();
            }, 1500);
            console.log(response);
        })
            .catch(function (error) {
                setTimeout(()=>{
                    setLoading(false);
                },1000);
                console.log(error);
            })
    }
    return(
        <div>
            {loading && <CircularProgress/>}
            {!loading &&
            <Grid container justify="center" spacing={2}>
                {props.products.map((product) => (
                    <Grid key={product.produsID} item>
                        <Product product={product} stoc={adaugaStoc} loading={loading}/>
                    </Grid>
                ))}
            </Grid>}
        </div>
    )
}


export default ProductsList;