package com.movieReviewApp.userms.util;




import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.movieReviewApp.userms.entity.User;
import com.movieReviewApp.userms.exception.InvalidTokenException;
import com.movieReviewApp.userms.exception.UserNotFoundException;
import com.movieReviewApp.userms.service.UserService;

import reactor.core.publisher.Mono;


@Component
public class AuthenticateFilter implements GatewayFilter {
    @Autowired
    private UserService service;

    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;

        public Config(String msg, boolean preLogger, boolean postLogger){
            this.baseMessage=msg;
            this.preLogger=preLogger;
            this.postLogger=postLogger;
        }

    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest httpRequest =  exchange.getRequest();
        ServerHttpResponse httpResponse =  exchange.getResponse();
        String token = httpRequest.getHeaders().getFirst("token");
        System.out.println("***inside filter request method=" + httpRequest.getMethod() + "url=" + httpRequest.getMethodValue() + " token=" + token);
        try {
            User user = service.authenticateByToken(token);
            httpResponse.getHeaders().set("username", user.getUsername());
            return chain.filter(exchange);

        } catch (InvalidTokenException | UserNotFoundException| SignatureException | MalformedJwtException e) {
            httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty();
        }

    }
}
