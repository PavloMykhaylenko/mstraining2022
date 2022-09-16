package com.microservice.training.gateway.util;

import com.microservice.training.gateway.dto.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

public class RequestEntityUtil {

    public static HttpEntity<Order> getRequestEntity(Order request) {
        return new HttpEntity<>(request, getHttpHeaders());
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
}
