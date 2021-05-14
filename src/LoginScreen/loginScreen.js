import React, {useState} from "react";
import './loginScreen.css'
import Logo from './georgeLogo150-150.png'
import {Button, Input, InputLabel, TextField} from "@material-ui/core";
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import footerBack from './footerBackground.jpeg';
import {useHistory} from "react-router";
import axios from 'axios';
import Shake from 'react-reveal/Shake';
import CircularProgress from '@material-ui/core/CircularProgress';
const LoginScreen = ()=>{
    const history=useHistory();
    const [loading,setLoading]=useState(false);
    const [username,setUsername]=useState('');
    const [password,setPassword]=useState('');
    const [error,setError]=useState(false);
    const LoginUrl='http://localhost:8080/firma/login';
    const onLogin= async e=>{
        e.preventDefault();
        setLoading(true);
        axios.post(LoginUrl, {
            username: username,
            password: password,
        })
            .then(function (response) {
                console.log(response);
                setTimeout(() => {
                    setLoading(false);
                    localStorage.setItem("user",JSON.stringify(response.data));
                    history.push(`/${response.data.type}Dashboard`);
                }, 1500);
                console.log(response);
            })
            .catch(function (error) {
                setTimeout(()=>{
                    setError(true);
                    setLoading(false);
                },1000);
                console.log(error);
            });
    }
    const onChangeUser = (e)=>{
        setUsername(e.target.value);
    }
    const onChangePass= (e)=>{
        setPassword(e.target.value);
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
                            onChange={onChangeUser}
                            placeholder="example@georgeinc.com"
                        />
                    </div>
                    <div className="userNameField">
                        <InputLabel htmlFor="password">Password</InputLabel>
                        <Input
                            id="password"
                            placeholder="Your password"
                            type={'password'}
                            onChange={onChangePass}
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
                        {loading && <CircularProgress size={24} className="buttonProgress" />}
                        {error && <Shake>
                            <h4 style={{color:'red'}}>Wrong username or password </h4>
                        </Shake>}
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