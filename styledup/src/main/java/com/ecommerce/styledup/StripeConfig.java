package com.ecommerce.styledup;

import com.stripe.Stripe;

import org.springframework.beans.factory.annotation.Value;
//import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class StripeConfig {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @jakarta.annotation.PostConstruct
    public void init() {
        Stripe.apiKey = secretKey; // secret key for Strip API
    }
    public String getSecretKey() {
        return secretKey;
    }
}

