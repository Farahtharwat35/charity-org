package com.charity_org.demo.Controllers;


import com.charity_org.demo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @GetMapping("/log")
    public String showcount(){
return Long.toString(User.getcount());
    }


//    @GetMapping("/create")
//    public String create(){
//        User.createUser();
//        return userService.getCount();
//    }


}
