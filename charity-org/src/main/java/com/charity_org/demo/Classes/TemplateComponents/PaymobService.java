package com.charity_org.demo.Classes.TemplateComponents;

import com.charity_org.demo.Classes.StrategyComponents.IPaymentMethod;
import com.charity_org.demo.Config.PaymobConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymobService extends PaymentTemplate{
    @Autowired
    private PaymobConfig paymobConfig;

    @Setter
    private String redirectUrl;

    @Getter
    private String paymobUrl;

    public String getAuthToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("api_key", paymobConfig.getApiKey());

        Map response = restTemplate.postForObject(paymobConfig.getAuthUrl(), requestBody, Map.class);
        return response.get("token").toString();
    }

    public int createOrder(String authToken, int amountCents) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("auth_token", authToken);
        requestBody.put("amount_cents", amountCents);
        requestBody.put("currency", "EGP");
        requestBody.put("items", new Object[]{}); // Add items if necessary

        Map response = restTemplate.postForObject(paymobConfig.getOrderUrl(), requestBody, Map.class);
        return (int) response.get("id");
    }

    public String generatePaymentKey(String authToken, int orderId, int amountCents, String redirectUrl) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> requestBody = new HashMap<>();

        // Include necessary parameters
        requestBody.put("auth_token", authToken);
        requestBody.put("amount_cents", amountCents);
        requestBody.put("order_id", orderId);
        requestBody.put("currency", "EGP");
        requestBody.put("integration_id", paymobConfig.getIntegrationId());
        requestBody.put("redirect_url", redirectUrl);

        // Add billing data
        Map<String, Object> billingData = new HashMap<>();
        billingData.put("first_name", "John");  // Replace with user's first name
        billingData.put("last_name", "Doe");   // Replace with user's last name
        billingData.put("email", "johndoe@example.com");  // Replace with user's email
        billingData.put("phone_number", "01012345678");  // Replace with user's phone number
        billingData.put("street", "1234 Elm St");  // Replace with street address
        billingData.put("city", "Cairo");  // Replace with city
        billingData.put("country", "EG");  // Replace with country code
        billingData.put("state", "Cairo");  // Replace with state
        billingData.put("postal_code", "12345");  // Replace with postal code

        // Add the missing fields (building, floor, apartment)
        billingData.put("building", "Building 5");  // Replace with building name/number
        billingData.put("floor", "3rd Floor");     // Replace with floor number
        billingData.put("apartment", "A2");        // Replace with apartment number

        // Add the billing data to the request body
        requestBody.put("billing_data", billingData);

        // Send the request and get the response
        Map response = restTemplate.postForObject(paymobConfig.getPaymentKeyUrl(), requestBody, Map.class);

        // Return the payment token (payment_key)
        return response.get("token").toString();
    }


    public String getPaymentLink(String paymentKey) {
        return paymobConfig.getPaymentPageUrl() + "/" + paymobConfig.getIframeId() + "?payment_token=" + paymentKey;
    }

    @Override
    protected boolean validatePaymentMethod(IPaymentMethod paymentMethod) {
        return true;
    }

    @Override
    protected boolean isPaymentMethodSaved(IPaymentMethod paymentMethod) {
        return false;
    }

    @Override
    protected void savePaymentMethod(IPaymentMethod paymentMethod) {
        // Paymob can't be saved
    }

    @Override
    protected boolean completePayment() {
        // Get Auth Token
        String authToken = getAuthToken();

        // Create Order
        int orderId = createOrder(authToken, 50);

        // Generate Payment Key with the Redirect URL
        String paymentKey = generatePaymentKey(authToken, orderId, 50, redirectUrl);

        // Get Payment Link
        this.paymobUrl = getPaymentLink(paymentKey);

        return true;
    }

}
