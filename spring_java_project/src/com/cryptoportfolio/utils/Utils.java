//package com.cryptoportfolio.utils;
//
//import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
//import com.google.gson.Gson;
//
//import java.util.Map;
//
//public class Utils {
//
//    private static final Gson gson = new Gson();
//
//    public static APIGatewayProxyResponseEvent buildResponse(int statusCode, Object body) {
//        return new APIGatewayProxyResponseEvent()
//                .withStatusCode(statusCode)
//                .withIsBase64Encoded(false)
//                .withHeaders(Map.of(
//                        "Access-Control-Allow-Origin","*",
//                        "Access-Control-Allow-Headers", "*",
//                        "Access-Control-Allow-Methods", "*",
//                        "Content-Type", "application/json"))
//                .withBody(gson.toJson(body));
//    }
//}
