package com.charity_org.demo.Controllers;


import com.charity_org.demo.Enums.DonationStatus;
import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Assigments;
import com.charity_org.demo.Models.Model.Donation;
import com.charity_org.demo.Models.Service.CourierService;
import com.charity_org.demo.Models.Service.DonationService;
import com.charity_org.demo.Models.Service.UserRoleService;
import com.charity_org.demo.Models.Service.UserService;
import com.charity_org.demo.Models.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courier")
public class CourierController {
    @Autowired
    private DonationService donationService;

    @Autowired
    private CourierService courierService;
    @Autowired
    CookieHandler cookieHandler;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard( HttpServletRequest request,Model model) {
        User user = cookieHandler.getUserFromSession(request);
        List<Donation> allDonations = donationService.getAllPendingDonations();

        // Fetch donations assigned to the courier
        List<Assigments> assignedDonations = courierService.getMyAssigments(user);
        List<Assigments> completedAssignments = assignedDonations.stream()
                .filter(assignment -> assignment.getDonation().displayDonationStatus().equals("Completed"))
                .collect(Collectors.toList());

        assignedDonations.removeAll(completedAssignments);

        //remove donations which found in my assigment
        allDonations.removeIf(donation -> assignedDonations.stream().anyMatch(assigment -> assigment.getDonation().getId() == donation.getId()));

        // Add donations to the model
        model.addAttribute("allDonations", allDonations);
        model.addAttribute("assignedDonations", assignedDonations);
        model.addAttribute("completedAssignments", completedAssignments);
        String name =userRoleService.getRole(request);
        model.addAttribute("role", name);
        model.addAttribute("userID", cookieHandler.getUserFromSession(request).getId());
        return "courier-dashboard";
    }


    @PostMapping("/assign/{donationId}")
    public String assignDonationToCourier(Model model, @PathVariable long donationId) {
        Donation donation = donationService.getDonation(donationId);
        User courier = userService.getUser(1);
        if (donation != null && courier != null) {
            donation.updateDonationStatus();
            donationService.updateDonationStatus(donation.getId(),donation.getDonationStatusClassName());
            courierService.assignCourierToDonation(courier, donation);
            model.addAttribute("success", "Donation assigned successfully");
            return "redirect:/courier/dashboard";
        } else {
            model.addAttribute("error", "Invalid donation or courier ID");
            return "redirect:/courier/dashboard";
        }
    }

    @PostMapping("/complete/{donationId}")
    public String completeDonation(Model model, @PathVariable long donationId) {
        Donation donation = donationService.getDonation(donationId);

        if (donation != null) {
            donation.updateDonationStatus();
            donationService.updateDonationStatus(donation.getId(),donation.getDonationStatusClassName());
        }
        return "redirect:/courier/dashboard";
    }
}
