package com.charity_org.demo.Classes.AdapterComponents;

import com.charity_org.demo.Models.Model.Address;
import org.springframework.beans.factory.annotation.Value;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UniversalTutorialAddressAdaptee {
    @Value("${address.api.key}")
    private String apiKey;
    @Value("${address.email}")
    private String userEmail;

    private String getAuthToken(){
        HttpResponse<JsonNode> response = Unirest.get("https://www.universal-tutorial.com/api/getaccesstoken")
                .header("Accept", "application/json")
                .header("api-token", apiKey)
                .header("user-email", userEmail)
                .asJson();
        if (response.isSuccess()){
            return response.getBody().getObject().getString("auth_token");
        }else{
            return "";
        }
    }
    public HttpResponse<JsonNode> getCountries(){
        String token = getAuthToken();
        return Unirest.get("https://www.universal-tutorial.com/api/countries/")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .asJson();
    }

    public HttpResponse<JsonNode> getStates(Address country)
    {
        String token = getAuthToken();
        return Unirest.get("https://www.universal-tutorial.com/api/states/"+country.getName())
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .asJson();

    }
}
