package com.cryptoportfolio.models;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetModel {

    private String assetId;
    private int rankByMarketCap;
    private double marketCap;
    private String assetImage;
    private String assetName;
    private String assetSymbol;
    private double totalSupply;
    private double usdValue;
    private double priceChangePercentage24h;

//    public AssetModel() {
//
//    }
//
//    public AssetModel(AssetModel.Builder builder) {
//        this.assetId = builder.assetId;
//        this.rankByMarketCap = builder.rankByMarketCap;
//        this.marketCap = builder.marketCap;
//        this.assetImage = builder.assetImage;
//        this.assetName = builder.assetName;
//        this.totalSupply = builder.totalSupply;
//        this.usdValue = builder.usdValue;
//        this.priceChangePercentage24h = builder.priceChangePercentage24h;
//    }
//
//
//    public static AssetModel.Builder builder() { return new AssetModel.Builder(); }
//
//    public static final class Builder {
//        private String assetId;
//        private int rankByMarketCap;
//        private double marketCap;
//        private String assetImage;
//        private String assetName;
//        private String assetSymbol;
//        private double totalSupply;
//        private double usdValue;
//        private double priceChangePercentage24h;
//
//        public AssetModel.Builder withAssetId(String assetIdToUse) {
//            this.assetId = assetIdToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withRankByMarketCap(int rankByMarketCapToUse) {
//            this.rankByMarketCap = rankByMarketCapToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withMarketCap(double marketCapToUse) {
//            this.marketCap = marketCapToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withAssetImage(String assetImageToUse) {
//            this.assetImage = assetImageToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withAssetSymbol(String assetSymbolToUse) {
//            this.assetSymbol = assetSymbolToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withAssetName(String assetNameToUse) {
//            this.assetName = assetNameToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withTotalSupply(double totalSupplyToUse) {
//            this.totalSupply = totalSupplyToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withUsdValue(double usdValueToUse) {
//            this.usdValue = usdValueToUse;
//            return this;
//        }
//
//        public AssetModel.Builder withPriceChangePercentage24h(double priceChangePercentage24h) {
//            this.priceChangePercentage24h = priceChangePercentage24h;
//            return this;
//        }
//
//        public AssetModel build() {return new AssetModel(this);}
//    }
//
//
//    public String getAssetId() {
//        return assetId;
//    }
//
//    public void setAssetId(String assetId) {
//        this.assetId = assetId;
//    }
//
//    public int getRankByMarketCap() {
//        return rankByMarketCap;
//    }
//
//    public void setRankByMarketCap(int rankByMarketCap) {
//        this.rankByMarketCap = rankByMarketCap;
//    }
//
//    public double getMarketCap() {
//        return marketCap;
//    }
//
//    public void setMarketCap(double marketCap) {
//        this.marketCap = marketCap;
//    }
//
//    public String getAssetImage() {
//        return assetImage;
//    }
//
//    public void setAssetImage(String assetImage) {
//        this.assetImage = assetImage;
//    }
//
//    public String getAssetName() {
//        return assetName;
//    }
//
//    public void setAssetName(String assetName) {
//        this.assetName = assetName;
//    }
//
//    public double getTotalSupply() {
//        return totalSupply;
//    }
//
//    public void setTotalSupply(double totalSupply) {
//        this.totalSupply = totalSupply;
//    }
//
//    public double getUsdValue() {
//        return usdValue;
//    }
//
//    public void setUsdValue(double usdValue) {
//        this.usdValue = usdValue;
//    }
//
//    public String getAssetSymbol() {
//        return assetSymbol;
//    }
//
//    public void setAssetSymbol(String assetSymbol) {
//        this.assetSymbol = assetSymbol;
//    }
//
//    public double getPriceChangePercentage24h() {
//        return priceChangePercentage24h;
//    }
//
//    public void setPriceChangePercentage24h(double priceChangePercentage24h) {
//        this.priceChangePercentage24h = priceChangePercentage24h;
//    }

}
