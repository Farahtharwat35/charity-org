package com.charity_org.demo.Controllers;


import com.charity_org.demo.Models.Service.RolesDecorator.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

}
