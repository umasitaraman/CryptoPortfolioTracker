package com.cryptoportfolio.settings;

import java.util.Set;

public class Settings {

    public static final Set<String> AVAILABLE_ASSETS = Set.of(
            "bitcoin",
            "ethereum",
            "tether",
            "binancecoin",
            "usd-coin",
            "ripple",
            "cardano",
            "solana",
            "avalanche-2",
            "terra-luna",
            "polkadot",
            "dogecoin",
            "binance-usd",
            "shiba-inu",
            "matic-network",
            "crypto-com-chain",
            "terrausd",
            "wrapped-bitcoin",
            "dai",
            "cosmos",
            "litecoin",
            "chainlink",
            "near",
            "tron",
            "ftx-token",
            "algorand",
            "bitcoin-cash",
            "staked-ether",
            "okb",
            "stellar",
            "leo-token",
            "fantom",
            "uniswap",
            "decentraland",
            "hedera-hashgraph",
            "internet-computer",
            "the-sandbox",
            "axie-infinity",
            "ethereum-classic",
            "elrond-erd-2",
            "vechain",
            "theta-token",
            "filecoin",
            "ecomi",
            "tezos",
            "klay-token",
            "monero",
            "compound-ether",
            "cdai",
            "the-graph"
    );

    public static final String AVAILABLE_ASSETS_STRING = String.join(", ", AVAILABLE_ASSETS);
}
