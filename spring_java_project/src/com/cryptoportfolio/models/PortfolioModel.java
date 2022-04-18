package com.cryptoportfolio.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioModel {
    private String username;
    private @Builder.Default Map<String, Double> assetQuantityMap = new HashMap<>();

//    public PortfolioModel() {
//
//    }

//    public PortfolioModel(Builder builder) {
//        this.username = builder.username;
//        this.assetQuantityMap = builder.assetQuantityMap;
//    }
//
//    public static Builder builder() { return new Builder(); }
//
//    public static final class Builder {
//        private String username;
//        private Map<String, Double> assetQuantityMap = new HashMap<>();
//
//        public Builder withUsername(String usernameToUse) {
//            this.username = usernameToUse;
//            return this;
//        }
//
//        public Builder withAssetQuantityMap(Map<String, Double> assetQuantityMap) {
//            this.assetQuantityMap = assetQuantityMap;
//            return this;
//        }
//
//
//        public PortfolioModel build() {return new PortfolioModel(this);}
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Map<String, Double> getAssetQuantityMap() {
//        return assetQuantityMap;
//    }
//
//    public void setAssetQuantityMap(Map<String, Double> assetQuantityMap) {
//        this.assetQuantityMap = assetQuantityMap;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof PortfolioModel)) return false;
//        PortfolioModel that = (PortfolioModel) o;
//        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAssetQuantityMap(), that.getAssetQuantityMap());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername(), getAssetQuantityMap());
//    }
//
//    @Override
//    public String toString() {
//        return "PortfolioModel{" +
//                "username='" + username + '\'' +
//                ", assetValue=" + assetQuantityMap +
//                '}';
//    }
}
