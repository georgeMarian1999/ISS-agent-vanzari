import React from "react";
import './loginScreen.css'
import Logo from './georgeLogo150-150.png'
import {Button, Input, InputLabel, TextField} from "@material-ui/core";
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import footerBack from './footerBackground.jpeg';
import {useHistory} from "react-router";
const LoginScreen = ( )=>{
    const history=useHistory();
    function onLogin(){
       history.push('/agentDashboard')
    }
    return(
            <div className="body">
                <header className="header">
                    <div className="logo">
                        <img src={Logo} alt="Logo"/>
                    </div>
                </header>
                <div className="formWrapper">
                    <h2>Sign in</h2>
                    <form className="form">
                        <div className="userNameField">
                            <InputLabel htmlFor="username">Username</InputLabel>
                            <Input
                                id="username"
                                type={'text'}
                                fullWidth
                                placeholder="example@georgeinc.com"
                        />
                    </div>
                    <div className="userNameField">
                        <InputLabel htmlFor="password">Password</InputLabel>
                        <Input
                            id="password"
                            placeholder="Your password"
                            type={'password'}
                            fullWidth
                        />
                    </div>
                    <div className="buttonWrapper">
                        <Button
                            onClick={onLogin}
                            variant="contained"
                            color="primary"
                            size="large"
                            fullWidth
                            startIcon={<ExitToAppIcon/>}>
                            Login
                        </Button>
                        </div>
                    </form>
                </div>
                <footer>
                    <img className="footerImg" src={footerBack}/>
                </footer>
            </div>
    )
}

export default LoginScreen;