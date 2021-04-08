import React from "react";
import Grid from "@material-ui/core/Grid";
import ButtonBase from "@material-ui/core/ButtonBase";
import Baterie from "../Images/baterie.jpeg";
import Typography from "@material-ui/core/Typography";
import Paper from "@material-ui/core/Paper";
import './productsScreen.css';
const Product = () =>{
    return(
        <div>
        <Paper className="paper">
            <Grid className="product" container spacing={2}>
                <Grid item>
                    <ButtonBase className="image">
                        <img className="img" alt="complex" src={Baterie} />
                    </ButtonBase>
                </Grid>
                <Grid item xs={12} sm container>
                    <Grid item xs container direction="column" spacing={2}>
                        <Grid item xs>
                            <Typography gutterBottom variant="subtitle1">
                                Standard license
                            </Typography>
                            <Typography variant="body2" gutterBottom>
                                Full resolution 1920x1080 • JPEG
                            </Typography>
                            <Typography variant="body2" color="textSecondary">
                                ID: 1030114
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Typography variant="body2" style={{ cursor: 'pointer' }}>
                                Buy Now
                            </Typography>
                        </Grid>
                    </Grid>
                    <Grid item>
                        <Typography variant="subtitle1">$19.00</Typography>
                    </Grid>
                </Grid>
            </Grid>
        </Paper>
        </div>
    )
}

export default Product;