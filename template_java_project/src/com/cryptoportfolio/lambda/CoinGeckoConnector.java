package com.cryptoportfolio.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.cryptoportfolio.dynamodb.dao.AssetDao;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.litesoftwares.coingecko.CoinGeckoApiClient;
import com.litesoftwares.coingecko.constant.Currency;
import com.litesoftwares.coingecko.domain.Coins.CoinMarkets;
import com.litesoftwares.coingecko.impl.CoinGeckoApiClientImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CoinGeckoConnector implements RequestHandler<ScheduledEvent, String> {

    @Override
    public String handleRequest(final ScheduledEvent event, Context context) {

        AssetDao assetDao = new AssetDao();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LambdaLogger logger = context.getLogger();
        CoinGeckoApiClient client = new CoinGeckoApiClientImpl();

        logger.log(gson.toJson(event));

        List<CoinMarkets> coinMarkets = client.getCoinMarkets(Currency.USD);
        List<Asset> assetsToSave = new ArrayList<>();

        for (var coinMarket : coinMarkets) {
            Asset asset = new Asset();
            asset.setAssetId(coinMarket.getId());
            asset.setRankByMarketCap((int) coinMarket.getMarketCapRank());
            asset.setAssetName(coinMarket.getName());
            asset.setAssetSymbol(coinMarket.getSymbol());
            asset.setMarketCap(coinMarket.getMarketCap());
            asset.setAssetImage(coinMarket.getImage());
            asset.setTotalSupply(coinMarket.getTotalSupply());
            asset.setUsdValue(coinMarket.getCurrentPrice());

            assetsToSave.add(asset);
        }

        assetDao.batchSaveAssets(assetsToSave);

        client.shutdown();

        return "200 OK";
    }

}