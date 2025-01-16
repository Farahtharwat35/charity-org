package com.charity_org.demo.Controllers;


import com.charity_org.demo.Middlware.cookies.CookieHandler;
import com.charity_org.demo.Models.Model.Event;
import com.charity_org.demo.Models.Model.Role;
import com.charity_org.demo.Models.Model.User;
import com.charity_org.demo.Models.Service.RoleService;
import com.charity_org.demo.Models.Service.UserRoleService;
import com.charity_org.demo.Models.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    CookieHandler cookieHandler;
  @Autowired
    UserRoleService userRoleService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        User user= cookieHandler.getUserFromSession(request);
        String name =userRoleService.getRole(request);
        model.addAttribute("role", name);
        model.addAttribute("userID", user.getId());
        return "HomePage";
    }
}
