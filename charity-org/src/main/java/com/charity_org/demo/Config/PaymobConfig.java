package com.charity_org.demo.Config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class PaymobConfig {

    // Getters for all properties
    @Value("${paymob.api_key}")
    private String apiKey;

    @Value("${paymob.integration_id}")
    private String integrationId;

    @Value("${paymob.iframe_id}")
    private String iframeId;

    @Value("${paymob.auth_url}")
    private String authUrl;

    @Value("${paymob.order_url}")
    private String orderUrl;

    @Value("${paymob.payment_key_url}")
    private String paymentKeyUrl;

    @Value("${paymob.payment_page_url}")
    private String paymentPageUrl;

}
