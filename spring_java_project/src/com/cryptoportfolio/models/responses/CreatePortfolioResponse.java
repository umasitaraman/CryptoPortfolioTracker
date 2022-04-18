package com.cryptoportfolio.models.responses;

/**
 * Builder class to build the result for the CreatePortfolioActivity using the request
 */
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePortfolioResponse {
        private String message;

//        public CreatePortfolioResponse(Builder builder) {
//            this.message = builder.message;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//
//        public static Builder builder() {return new Builder();}
//
//        public static final class Builder {
//            private String message;
//
//            public Builder withMessage(String message) {
//                this.message = message;
//                return this;
//            }
//
//            public CreatePortfolioResponse build() {return new CreatePortfolioResponse(this);}
//        }
}
