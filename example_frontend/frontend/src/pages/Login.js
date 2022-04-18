import React, {useState} from 'react';
import {NavLink, useNavigate} from "react-router-dom";
<<<<<<< HEAD
import axios from 'axios';
import {setUserSession} from '../service/AuthService';

const loginAPIUrl = 'https://r6z0a5xu3f.execute-api.us-east-2.amazonaws.com/prod/login/';
=======
import {setUserSession} from '../service/authService';
import axios from '../apis/cryptoPortfolio';
import {APIKey} from "../apis/apiKey";
>>>>>>> main

const Login = (props) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState(null);
    const navigate = useNavigate();

    const submitHandler = (event) => {
        const requestConfig = {
            headers: {
<<<<<<< HEAD
                'x-api-key': 'Lg6TGbdNQBTq3IMNsQ9c5dCFEUpgXQS5IG5o7RZ5'
=======
                'x-api-key': APIKey
>>>>>>> main
            }
        }
        const requestBody = {
            username: username,
            password: password
        }

        axios.post('/login', requestBody, requestConfig).then((response) => {
            setUserSession(response.data.username, response.data.authToken, response.data.newUser);
            console.log("newUser: " + response.data.newUser);
            console.log("typeof newUser: " + typeof response.data.newUser);
            props.authenticate();
            navigate('/portfolio');
        }).catch((error) => {
            console.log('Error ' + error.toJSON())
            console.log(error.response)
            setMessage(error.response.data.errorMessage.split('] ')[1]);
        })
    }

    return (
        <div>
            <div className="header">
                <NavLink className="active" to="/register">Register</NavLink>
                <NavLink className="active" to="/login">Login</NavLink>
            </div>
            <div id="alignpage">
                <h4>Login</h4>
                username: <input className="field" type="text" value={username} onChange={event => setUsername(event.target.value)} /> <br/>
                password: <input className="field" type="password" value={password} onChange={event => setPassword(event.target.value)} /> <br/>
            </div>
            <div id="outer">
                <input className="inner" type="button" value="Login" onClick={submitHandler} />
            </div>
            {message && <p className="message">{message}</p>}
        </div>
    )
}

export default Login;