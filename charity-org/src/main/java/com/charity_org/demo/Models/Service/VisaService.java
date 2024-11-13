package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Paypal;
import com.charity_org.demo.Models.VISA;
import com.charity_org.demo.Models.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class VisaService implements IPaymentMethodService{

    @Autowired
    private VisaRepository visaRepository;
    @Override
    public boolean processPayment(@RequestBody Map<String, Object> jsonMap) {
        if (jsonMap.containsKey("cvv") && jsonMap.containsKey("cardNumber")) {
            try {
                // Parse the CVV as an integer
                int cvv = Integer.parseInt((String) jsonMap.get("cvv"));
                String cardNumber = (String) jsonMap.get("cardNumber");

                // Validate card number and CVV length
                if (Integer.toString(cvv).length() == 3 && cardNumber != null && !cardNumber.isEmpty()) {
                    VISA visa = new VISA();
                    visa.setCvv(cvv);
                    visa.setCardNumber(cardNumber);

                    // Set other fields if available
                    if (jsonMap.containsKey("fName")) visa.setFName((String) jsonMap.get("fName"));
                    if (jsonMap.containsKey("middleName")) visa.setMiddleName((String) jsonMap.get("middleName"));
                    if (jsonMap.containsKey("lName")) visa.setLName((String) jsonMap.get("lName"));

                    // Convert expirationDate from String to Date
                    if (jsonMap.containsKey("expirationDate")) {
                        String expirationDateStr = (String) jsonMap.get("expirationDate");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date expirationDate = formatter.parse(expirationDateStr);
                            visa.setExpirationDate(expirationDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return false; // handle parsing error appropriately
                        }
                    }

                    // Save VISA entity
                    visaRepository.save(visa);
                    return true;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false; // handle incorrect cvv format
            }
        }
        return false;
    }
}

