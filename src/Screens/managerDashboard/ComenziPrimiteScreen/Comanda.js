import React from "react";
import {useState} from "react";
import Grid from "@material-ui/core/Grid";
import Slide from '@material-ui/core/Slide';
import Typography from "@material-ui/core/Typography";
import Paper from "@material-ui/core/Paper";
import './productsScreen.css';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import {CircularProgress, Input} from "@material-ui/core";
import {Label} from "@material-ui/icons";
const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});
const Comanda = (props) =>{
    const [open, setOpen] = React.useState(false);
    const [loading,setLoading]=useState(false);
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    const handleConfirmare = () =>{
        setLoading(true);
        props.confirmare(props.comanda.comandaID);
        setTimeout(()=>{
            setLoading(false);
            setOpen(false);
        },1500);
    }
    const handleRetur = () =>{
        setLoading(true);
        props.retur(props.comanda.comandaID);
        setTimeout(()=>{
            setLoading(false);
            setOpen(false);
        },2000);
    }
    return(
        <div>
            <Paper className="paper">
                <Grid className="product" container spacing={2}>
                    <Grid item xs={12} sm container>
                        <Grid item xs container direction="column" spacing={2}>
                            <Grid item xs>
                                <Typography gutterBottom variant="subtitle1">
                                    {props.comanda.data}
                                </Typography>
                                <Typography variant="body2" gutterBottom>
                                    Cantitate {props.comanda.cantitate} buc.
                                </Typography>
                                <Typography variant="body2" style={{padding: 10}} color="textSecondary">
                                   Status comanda: {props.comanda.status}
                                </Typography>
                            </Grid>
                            <Grid item>
                                {props.comanda.status=="Procesare"&&
                                <Typography onClick={handleClickOpen} variant="body2" style={{ cursor: 'pointer',color:'blue' }}>
                                    Confirma
                                </Typography>}
                                {props.comanda.status=="Returnare"&&
                                <Typography onClick={handleClickOpen} variant="body2" style={{ cursor: 'pointer',color:'blue' }}>
                                    Confirma retur
                                </Typography>}
                                {props.comanda.status=="Confirmata"&&
                                <Typography  variant="body2" >
                                    Confirmata
                                </Typography>}
                                {props.comanda.status=="Returnata"&&
                                <Typography  variant="body2" >
                                    Returnata
                                </Typography>}
                                {props.comanda.status=="Anulata"&&
                                <Typography  variant="body2" >
                                    Anulata
                                </Typography>}
                            </Grid>
                        </Grid>
                        <Grid item>
                            <Typography style={{padding: 10}} variant="subtitle1">{props.comanda.valoare}lei</Typography>
                        </Grid>
                    </Grid>
                </Grid>
            </Paper>
            <div>
                <Dialog
                    open={open}
                    TransitionComponent={Transition}
                    keepMounted
                    onClose={handleClose}
                    aria-labelledby="alert-dialog-slide-title"
                    aria-describedby="alert-dialog-slide-description"
                >
                    <DialogTitle id="alert-dialog-slide-title">{"Confirma actiunea pe comanda"}</DialogTitle>
                    <DialogContent className="dialogcontent">
                        <DialogContentText>Comanda din data:{props.comanda.data}</DialogContentText>
                        <DialogContentText>Cantitate {props.comanda.cantitate}</DialogContentText>
                        <DialogContentText>Pret total {props.comanda.valoare} lei</DialogContentText>
                        {loading && <CircularProgress/>}
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Cancel
                        </Button>
                        {props.comanda.status=="Procesare"&&
                        <Button onClick={handleConfirmare} color="primary">
                            Confirma
                        </Button>}
                        {props.comanda.status=="Returnare"&&
                        <Button onClick={handleRetur} color="primary">
                            Returneaza
                        </Button>}

                    </DialogActions>
                </Dialog>
            </div>
        </div>
    )
}

export default Comanda;