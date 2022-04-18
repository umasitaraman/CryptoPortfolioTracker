package com.cryptoportfolio.models.responses;

import com.cryptoportfolio.models.PortfolioModel;

import java.util.Objects;

/**
 * Builder class to fetch the result for the GetPortfolioActivity using the request
 */
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPortfolioResponse {
    private PortfolioModel portfolio;

//    public GetPortfolioResponse() {
//
//    }
//
//    public GetPortfolioResponse(Builder builder) {
//        this.portfolio = builder.portfolio;
//    }
//
//    public PortfolioModel getPortfolio() {
//        return portfolio;
//    }
//
//    public void setPortfolio(PortfolioModel portfolio) {
//        this.portfolio = portfolio;
//    }
//
//    public static Builder builder() {return new Builder();}
//
//    public static final class Builder {
//        private PortfolioModel portfolio;
//
//        public Builder withPortfolio(PortfolioModel playlistToUse) {
//            this.portfolio = playlistToUse;
//            return this;
//        }
//
//        public GetPortfolioResponse build() {return new GetPortfolioResponse(this);}
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof GetPortfolioResponse)) return false;
//        GetPortfolioResponse that = (GetPortfolioResponse) o;
//        return Objects.equals(getPortfolio(), that.getPortfolio());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getPortfolio());
//    }
//
//    @Override
//    public String toString() {
//        return "GetPortfolioResponse{" +
//                "portfolio=" + portfolio +
//                '}';
//    }
}

