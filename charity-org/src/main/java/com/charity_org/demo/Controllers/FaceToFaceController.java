package com.charity_org.demo.Controllers;

import com.charity_org.demo.Models.FaceToFace;
import com.charity_org.demo.Models.Paypal;
import com.charity_org.demo.Models.Service.FaceToFaceService;
import com.charity_org.demo.Models.Service.PaypalService;
import com.charity_org.demo.Models.repository.FaceToFaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facetoface")
public class FaceToFaceController {

    @Autowired
    private FaceToFaceService FaceService;


    // POST Request to handle form submission
    @PostMapping("/save")
    public String processPayment(Model model) {
        FaceToFace faceToFace = new FaceToFace();
        boolean result = FaceService.processPayment(faceToFace);

        if (result) {
            model.addAttribute("message", "Payment processed successfully!");
        } else {
            model.addAttribute("message", "Payment processing failed. Please try again.");
        }
        return "Confirmation";
    }
}
