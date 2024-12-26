package com.charity_org.demo.Controllers;


import com.charity_org.demo.Models.Model.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "HomePage";
    }
}
