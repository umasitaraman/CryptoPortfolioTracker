package com.cryptoportfolio.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * This is the Dynamo DB mapper class for the Asset Table
 */

@DynamoDBTable(tableName = "assets")
public class Asset {
    String assetId;
    Integer rankByMarketCap;
    String assetName;
    String assetSymbol;
    Double marketCap;
    String assetImage;
    Double totalSupply;
    Double usdValue;
    Double priceChangePercentage24h;


    @DynamoDBHashKey(attributeName = "asset_id")
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @DynamoDBAttribute(attributeName = "rank_by_market_cap")
    public Integer getRankByMarketCap() {
        return rankByMarketCap;
    }

    public void setRankByMarketCap(Integer rankByMarketCap) {
        this.rankByMarketCap = rankByMarketCap;
    }

    @DynamoDBAttribute(attributeName = "asset_name")
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    @DynamoDBAttribute(attributeName = "asset_symbol")
    public String getAssetSymbol() {
        return assetSymbol;
    }

    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }

    @DynamoDBAttribute(attributeName = "market_cap")
    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    @DynamoDBAttribute(attributeName = "asset_image")
    public String getAssetImage() {
        return assetImage;
    }

    public void setAssetImage(String assetImage) {
        this.assetImage = assetImage;
    }

    @DynamoDBAttribute(attributeName = "total_supply")
    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    @DynamoDBAttribute(attributeName = "usd_value")
    public Double getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(Double usdValue) {
        this.usdValue = usdValue;
    }

    @DynamoDBAttribute(attributeName = "price_change_percentage_24h")
    public Double getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public void setPriceChangePercentage24h(Double priceChangePercentage24h) {
        this.priceChangePercentage24h = priceChangePercentage24h;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Objects.equals(getAssetId(), asset.getAssetId()) && Objects.equals(getRankByMarketCap(), asset.getRankByMarketCap()) && Objects.equals(getAssetName(), asset.getAssetName()) && Objects.equals(getAssetSymbol(), asset.getAssetSymbol()) && Objects.equals(getMarketCap(), asset.getMarketCap()) && Objects.equals(getAssetImage(), asset.getAssetImage()) && Objects.equals(getTotalSupply(), asset.getTotalSupply()) && Objects.equals(getUsdValue(), asset.getUsdValue()) && Objects.equals(getPriceChangePercentage24h(), asset.getPriceChangePercentage24h());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAssetId(), getRankByMarketCap(), getAssetName(), getAssetSymbol(), getMarketCap(), getAssetImage(), getTotalSupply(), getUsdValue(), getPriceChangePercentage24h());
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId='" + assetId + '\'' +
                ", rankByMarketCap=" + rankByMarketCap +
                ", assetName='" + assetName + '\'' +
                ", assetSymbol='" + assetSymbol + '\'' +
                ", marketCap=" + marketCap +
                ", assetImage='" + assetImage + '\'' +
                ", totalSupply=" + totalSupply +
                ", usdValue=" + usdValue +
                ", priceChangePercentage24h=" + priceChangePercentage24h +
                '}';
    }
}
