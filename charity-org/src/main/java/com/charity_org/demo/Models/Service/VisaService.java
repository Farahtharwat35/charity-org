package com.charity_org.demo.Models.Service;

import com.charity_org.demo.Models.Paypal;
import com.charity_org.demo.Models.VISA;
import com.charity_org.demo.Models.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Map;

@Service
public class VisaService implements IPaymentMethodService{

    @Autowired
    private VisaRepository visaRepository;
    @Override
    public boolean processPayment(@RequestBody Map<String, String> jsonMap) {
        if (jsonMap.containsKey("cvv") && jsonMap.containsKey("cardNumber")) {
            String cvv = (String) jsonMap.get("cvv");
            int cvv_int = Integer.parseInt(cvv);
            String cardNumber = (String) jsonMap.get("cardNumber");

            if (cvv.toString().length() == 3 && cardNumber != null && !cardNumber.isEmpty()) {
                VISA visa = new VISA();
                visa.setCvv(cvv_int);
                visa.setCardNumber(cardNumber);

                // Set other fields if available
                if (jsonMap.containsKey("fName")) visa.setFName((String) jsonMap.get("fName"));
                if (jsonMap.containsKey("middleName")) visa.setMiddleName((String) jsonMap.get("middleName"));
                if (jsonMap.containsKey("lName")) visa.setLName((String) jsonMap.get("lName"));
                //if (jsonMap.containsKey("expirationDate")) visa.setExpirationDate((Date) jsonMap.get("expirationDate"));

                // Save VISA entity
                visaRepository.save(visa);
                return true;
            }
        }
        return false;
    }
}

