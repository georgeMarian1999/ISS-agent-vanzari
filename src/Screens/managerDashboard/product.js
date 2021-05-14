import React from "react";
import {useState} from "react";
import Grid from "@material-ui/core/Grid";
import Slide from '@material-ui/core/Slide';
import Typography from "@material-ui/core/Typography";
import Paper from "@material-ui/core/Paper";
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import {CircularProgress, Input} from "@material-ui/core";
const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});
const Product = (props) =>{
    const [open, setOpen] = React.useState(false);
    const [cantitate,setCantitate]=useState(1);
    const [loading,setLoading]=useState(false);
    const [total,setTotal]=useState(cantitate*props.product.pret);
    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };
    const handleAdaugaStoc = () =>{
        setLoading(true);
        props.stoc(props.product.produsID,cantitate);

        setTimeout(()=>{
            setLoading(false);
            setOpen(false);
        },1500);
    }
    const onChangeCant = (e)=>{
        setCantitate(e.target.value);
        setTotal(e.target.value*props.product.pret);
    }
    return(
        <div>
            <Paper className="paper">
                <Grid className="product" container spacing={2}>
                    <Grid item xs={12} sm container>
                        <Grid item xs container direction="column" spacing={2}>
                            <Grid item xs>
                                <Typography gutterBottom variant="subtitle1">
                                    {props.product.numeProdus}
                                </Typography>
                                <Typography variant="body2" gutterBottom>
                                    {props.product.descriereProdus}
                                </Typography>
                                <Typography variant="body2" style={{padding: 10}} color="textSecondary">
                                   In stoc: {props.product.stoc}
                                </Typography>
                            </Grid>
                            <Grid item>
                                <Typography onClick={handleClickOpen} variant="body2" style={{ cursor: 'pointer' }}>
                                    Adauga stoc
                                </Typography>
                            </Grid>
                        </Grid>
                        <Grid item>
                            <Typography style={{padding: 10}} variant="subtitle1">{props.product.pret} lei</Typography>
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
                    <DialogTitle id="alert-dialog-slide-title">{"Confirma adauga stoc"}</DialogTitle>
                    <DialogContent className="dialogcontent">
                        <DialogContentText>{props.product.numeProdus}</DialogContentText>
                        <DialogContentText>Cantitate</DialogContentText>
                        {loading && <CircularProgress/>}
                        {!loading &&<Input
                            type={'text'}
                            fullWidth
                            value={cantitate}
                            onChange={onChangeCant}
                            placeholder="ex. 5"
                        />}
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Cancel
                        </Button>
                        <Button onClick={handleAdaugaStoc} color="primary">
                            Adauga stoc
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </div>
    )
}

export default Product;