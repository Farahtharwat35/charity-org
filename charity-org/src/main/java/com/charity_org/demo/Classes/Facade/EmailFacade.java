package com.charity_org.demo.Classes.Facade;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class EmailFacade {

    private static EmailFacade instance;

    @Value("${mailgun.api.base-url}")
    private String baseUrl;

    @Value("${mailgun.api.key}")
    private String apiKey;

    @Value("${mailgun.domain}")
    private String domain;


    public boolean sendEmail(String recipient, String subject, String body) {
        String url = String.format("%s%s/messages", baseUrl, domain);

        try {
            HttpResponse<JsonNode> request = Unirest.post(url)
                    .basicAuth("api", apiKey)
                    .queryString("from", "Charity Org <postmaster@" + domain + ">")
                    .queryString("to", recipient)
                    .queryString("subject", subject)
                    .queryString("text", body)
                    .asJson();

            System.out.println("Response: " + request.getBody().toString());
            return request.getStatus() == 200;

        } catch (UnirestException e) {
            e.printStackTrace();
            return false;
        }
    }
}
