package com.cryptoportfolio.models.responses;

import java.util.Objects;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FailureResponse {

    private String message;

//    public FailureResponse(String message) {
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        FailureResponse that = (FailureResponse) o;
//        return Objects.equals(getMessage(), that.getMessage());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getMessage());
//    }
//
//    @Override
//    public String toString() {
//        return "FailureResponse{" +
//                "message='" + message + '\'' +
//                '}';
//    }
}
