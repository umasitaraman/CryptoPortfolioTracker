package com.cryptoportfolio;

import com.cryptoportfolio.settings.Settings;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.List;
import java.util.Set;

public class CoingeckoAPITest {

    public static void main(String[] args) {

        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

        List<CoinMarkets> coinMarkets = client.getCoinMarkets(Currency.USD, Settings.AVAILABLE_ASSETS_STRING,
                null, null, null, false, null);

        List<String> availableAssets = List.of(
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

        System.out.println(String.join(", ", availableAssets));
        for (var a : availableAssets) {
            System.out.print("\"" + a + "\", ");
        }

        for (var cm : coinMarkets) {
            System.out.println(cm);
        }

        client.shutdown();

    }


}
