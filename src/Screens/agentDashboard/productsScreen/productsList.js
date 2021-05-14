import React, {useEffect, useState} from "react";
import Grid from '@material-ui/core/Grid';
import './productsScreen.css';
import Product from "./product";
import {CircularProgress} from "@material-ui/core";
import axios from "axios";
const ProductsList = ()=>{
    const User=JSON.parse(localStorage.getItem("user"));
    const [products,setProducts]=useState([]);
    const [loading,setLoading]=useState(false);
    const productsURL='http://localhost:8080/firma/operations/products';
    const plasareURL='http://localhost:8080/firma/operations/comanda';
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
    const plasareComanda = async (produsID,cantitate)=>{
        await axios.post(plasareURL,{
                produsID: produsID,
                cant: cantitate,
                userID: User.userID,
                data:new Date().toDateString(),
            }
        ).then(function (response) {
            console.log(response);
            setTimeout(() => {
                getProducts();
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
                {products.map((product) => (
                    <Grid key={product.produsID} item>
                        <Product product={product} comanda={plasareComanda} loading={loading} getproducts={getProducts}/>
                    </Grid>
                ))}
            </Grid>}
        </div>
    )
}


export default ProductsList;