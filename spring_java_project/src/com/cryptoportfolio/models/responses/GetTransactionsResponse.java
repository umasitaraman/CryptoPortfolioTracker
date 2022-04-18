package com.cryptoportfolio.models.responses;
import com.cryptoportfolio.models.TransactionModel;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetTransactionsResponse {
    private List<TransactionModel> transactions;

//    public GetTransactionsResponse() {
//
//    }
//
//    public GetTransactionsResponse(Builder builder) {
//        this.transactions = builder.transactions;
//    }
//
//    public List<TransactionModel> getTransactions() {
//        return transactions;
//    }
//
//    public void setTransactions(List<TransactionModel> transactions) {
//        this.transactions = transactions;
//    }
//
//    public static Builder builder() {return new Builder();}
//
//    public static final class Builder {
//        private List<TransactionModel> transactions;
//
//        public Builder withTransactions(List<TransactionModel> transactionsToUse) {
//            this.transactions = transactionsToUse;
//            return this;
//        }
//
//        public GetTransactionsResponse build() {return new GetTransactionsResponse(this);}
//    }
}
