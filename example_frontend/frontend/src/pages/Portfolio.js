import React, {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {getToken, getUsername, isNewUser, resetUserSession} from '../service/authService';
import PortfolioList from "../components/PortfolioList";
import PortfolioChart from "../components/PortfolioChart";
<<<<<<< HEAD

const portfolioAPIUrl = 'https://r6z0a5xu3f.execute-api.us-east-2.amazonaws.com/prod/portfolio/';
=======
import coinGecko from "../apis/coinGecko";
import axios from '../apis/cryptoPortfolio';
import {APIKey} from "../apis/apiKey";
>>>>>>> main

const Portfolio = (props) => {
    const username = getUsername();
    const navigate = useNavigate();
    const token = getToken();
    const newUser = !!isNewUser();


    const [message, setMessage] = useState(null);
    const [assets, setAssets] = useState(null);
    const [assetMap, setAssetMap] = useState(null);
    const [assetQuantityMap, setAssetQuantityMap] = useState(null);
    const [isLoading, setIsLoading] = useState(false);

    const masterList = [
        "bitcoin", "ethereum", "tether",
        "binancecoin", "usd-coin", "ripple",
        "cardano", "solana", "avalanche-2",
        "terra-luna", "polkadot", "dogecoin",
        "binance-usd", "shiba-inu", "matic-network",
        "crypto-com-chain", "terrausd", "wrapped-bitcoin",
        "dai", "cosmos", "litecoin",
        "chainlink", "near", "tron",
        "ftx-token", "algorand", "bitcoin-cash",
        "staked-ether", "okb", "stellar",
        "leo-token", "fantom", "uniswap",
        "decentraland", "hedera-hashgraph", "internet-computer",
        "the-sandbox", "axie-infinity", "ethereum-classic",
        "elrond-erd-2", "vechain", "theta-token",
        "filecoin", "ecomi", "tezos",
        "klay-token", "monero", "compound-ether",
        "cdai", "the-graph"
    ];

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            const response = await coinGecko.get("/coins/markets/", {
                params: {
                    vs_currency: "usd",
                    ids: masterList.join(","),
                },
            });
            setAssets(response.data);
            setAssetMap(Object.fromEntries(response.data.map(asset => [asset.id, asset])));
            setIsLoading(false);
        };
        fetchData();
    },[]);

    useEffect(() => {
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
        
        if (!newUser) {
            axios.get('/portfolio/' + username, requestConfig).then((response) => {
                console.log(response);
                setAssetQuantityMap(response.data.portfolio.assetQuantityMap);
            }).catch((error) => {
                if (error.response.status === 401 || error.response.status === 403) {
                    resetUserSession();
                    props.logout();
                    navigate('/login');
                } else {
                    setMessage(error.response.data.errorMessage.split('] ')[1]);
                }
            })
        }
    },[]);

    const createHandler = () => {
        console.log(assets);
        console.log(assetMap);
        navigate('/createPortfolio', {state : {assets:assets, assetMap:assetMap}});
    }

    const updateHandler = () => {
        navigate('/updatePortfolio', {state : {assets:assets, assetMap:assetMap, assetQuantityMap:assetQuantityMap}});
    }

    const transactionHandler = () => {
        console.log(assets);
        console.log(assetMap);
        navigate('/transactionHistory', {state : {assets:assets.filter(asset=>assetQuantityMap[asset.id]), assetMap:assetMap}});
    }

    const logoutHandler = () => {
        resetUserSession();
        props.logout();
        navigate('/login');
    }
    return (
        <div>
            {(assets && assetQuantityMap) &&
                <PortfolioChart assets={assets.filter(asset => assetQuantityMap[asset.id])} assetQuantityMap={assetQuantityMap} />}
            {(assets && assetQuantityMap) ?
                <PortfolioList assets={assets.filter(asset => assetQuantityMap[asset.id])} assetQuantityMap={assetQuantityMap} />:
            <div>{newUser ? "" : "Loading..."}</div>}

            <div id="outer">
                {newUser && <input className="inner" type="button" value="Create Portfolio" onClick={createHandler} />} <br/>
                {!newUser && <input className="inner" type="button" value="Update Portfolio" onClick={updateHandler} />} <br/>
                {!newUser && <input className="inner" type="button" value="Transaction History" onClick={transactionHandler} />} <br/>
                <input className="inner" type="button" value="Logout" onClick={logoutHandler} />
            </div>
            {message && <p className="message">{message}</p>}
        </div>
    )
}

export default Portfolio;
