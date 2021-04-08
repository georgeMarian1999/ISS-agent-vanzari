import React from "react";
import Grid from '@material-ui/core/Grid';
import './productsScreen.css';
import Product from "./product";
const ProductsList = ()=>{
    return(
        <div>
        <Grid container justify="center" spacing={2}>
                {[0, 1, 2,3,4,5,6,7,8].map((value) => (
                    <Grid key={value} item>
                        <Product/>
                    </Grid>
                ))}
        </Grid>
        </div>
    )
}


export default ProductsList;