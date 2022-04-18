package com.cryptoportfolio.models;

import java.util.Objects;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionModel {
    private String username;
    private String transactionDate;
    private String assetId;
    private double assetQuantity;
    private double transactionValue;
    private String transactionType;

//    public TransactionModel() {
//
//    }
//
//    public TransactionModel(Builder builder) {
//        this.username = builder.username;
//        this.transactionDate = builder.transactionDate;
//        this.assetId = builder.assetId;
//        this.assetQuantity = builder.assetQuantity;
//        this.transactionValue = builder.transactionValue;
//        this.transactionType = builder.transactionType;
//    }
//
//    public static Builder builder() { return new Builder(); }
//
//    public static final class Builder {
//        private String username;
//        private String transactionDate;
//        private String assetId;
//        private double assetQuantity;
//        private double transactionValue;
//        private String transactionType;
//
//        public Builder withUsername(String usernameToUse) {
//            this.username = usernameToUse;
//            return this;
//        }
//
//        public Builder withTransactionDate(String transactionDateToUse) {
//            this.transactionDate = transactionDateToUse;
//            return this;
//        }
//
//        public Builder withAssetId(String assetIdToUse) {
//            this.assetId = assetIdToUse;
//            return this;
//        }
//
//        public Builder withAssetQuantity(double assetQuantityToUse) {
//            this.assetQuantity = assetQuantityToUse;
//            return this;
//        }
//
//        public Builder withTransactionValue(double transactionValueToUse) {
//            this.transactionValue = transactionValueToUse;
//            return this;
//        }
//
//        public Builder withTransactionType(String transactionTypeToUse) {
//            this.transactionType = transactionTypeToUse;
//            return this;
//        }
//
//
//        public TransactionModel build() {return new TransactionModel(this);}
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
//    public String getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(String transactionDate) {
//        this.transactionDate = transactionDate;
//    }
//
//    public String getAssetId() {
//        return assetId;
//    }
//
//    public void setAssetId(String assetId) {
//        this.assetId = assetId;
//    }
//
//    public double getAssetQuantity() {
//        return assetQuantity;
//    }
//
//    public void setAssetQuantity(double assetQuantity) {
//        this.assetQuantity = assetQuantity;
//    }
//
//    public double getTransactionValue() {
//        return transactionValue;
//    }
//
//    public void setTransactionValue(double transactionValue) {
//        this.transactionValue = transactionValue;
//    }
//
//    public String getTransactionType() {
//        return transactionType;
//    }
//
//    public void setTransactionType(String transactionType) {
//        this.transactionType = transactionType;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof TransactionModel)) return false;
//        TransactionModel that = (TransactionModel) o;
//        return Double.compare(that.getAssetQuantity(), getAssetQuantity()) == 0 && Double.compare(that.getTransactionValue(), getTransactionValue()) == 0 && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getTransactionDate(), that.getTransactionDate()) && Objects.equals(getAssetId(), that.getAssetId()) && Objects.equals(getTransactionType(), that.getTransactionType());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getUsername(), getTransactionDate(), getAssetId(), getAssetQuantity(), getTransactionValue(), getTransactionType());
//    }
//
//    @Override
//    public String toString() {
//        return "TransactionModel{" +
//                "username='" + username + '\'' +
//                ", transactionDate='" + transactionDate + '\'' +
//                ", assetId='" + assetId + '\'' +
//                ", assetQuantity=" + assetQuantity +
//                ", transactionValue=" + transactionValue +
//                ", transactionType='" + transactionType + '\'' +
//                '}';
//    }
}
