import {BrowserRouter, NavLink, Route, Routes, Navigate} from "react-router-dom";
import React, {useEffect, useState} from "react";

import Home from "./pages/Home";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Portfolio from "./pages/Portfolio";
import {getUsername, getToken, setUserSession, resetUserSession} from './service/authService';
import CreatePortfolio from "./pages/CreatePortfolio";
import "./App.css";
import TransactionHistory from "./pages/TransactionHistory";
import UpdatePortfolio from "./pages/UpdatePortfolio";
<<<<<<< HEAD

const verifyTokenAPIUrl = 'https://r6z0a5xu3f.execute-api.us-east-2.amazonaws.com/prod/verify';
=======
import axios from './apis/cryptoPortfolio.js';
import {APIKey} from "./apis/apiKey";
>>>>>>> main

function App() {

    const [isAuthentication, setAuthentication] = useState(null);
    const [isTokenSet, setToken] = useState(getToken());

    useEffect(() => {
        const token = getToken();
        if (token === 'undefined' || token === undefined || token === null || !token) {
            return;
        }

        const requestConfig = {
            headers: {
<<<<<<< HEAD
                'x-api-key': 'Lg6TGbdNQBTq3IMNsQ9c5dCFEUpgXQS5IG5o7RZ5',
=======
                'x-api-key': APIKey,
>>>>>>> main
                'cp-auth-token': token
            }
        }

        const requestBody = {
            username: getUsername(),
            token: token
        }

        axios.post('/verify', requestBody, requestConfig).then((response) => {
            setUserSession(response.data.username, response.data.token);
            setAuthentication(false);
        }).catch(() => {
            resetUserSession();
            setAuthentication(false);
        })
    }, []);

    const token = getToken();
    if (isAuthentication && token) {
        return <div className="content">Authentication...</div>
    }

    return (
        <div className="container">
            <BrowserRouter>
                <div className="header">
                     <h4 className="text" >Crypto Portfolio Tracker</h4>
                    {/*<NavLink className="active" to="/">Home</NavLink>*/}
                </div>

                <div className="container">
                    <Routes>
                        {!isTokenSet && (
                            <>
                                <Route path="/" element={<Home />}/>
                                <Route path="/register" element={<Register />}/>
                                <Route path="/login" element={<Login authenticate={() => setToken(getToken())} />}/>
                            </>
                        )}
                        {isTokenSet && (
                            <>
                                <Route path="/portfolio" element={<Portfolio logout={() => setToken(getToken())} />}/>
                                <Route path="/createPortfolio" element={<CreatePortfolio logout={() => setToken(getToken())} />}/>
                                <Route path="/transactionHistory" element={<TransactionHistory logout={() => setToken(getToken())} />}/>
                                <Route path="/updatePortfolio" element={<UpdatePortfolio logout={() => setToken(getToken())} />}/>
                            </>
                        )}
                        <Route path="*" element={<Navigate to={isTokenSet ? "/portfolio" : "/"}/>}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </div>
    );
}

export default App;
