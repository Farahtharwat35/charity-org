//package com.charity_org.demo.Controllers;
//
//
//import com.charity_org.demo.Enums.EventStatus;
//import com.charity_org.demo.Models.Event;
//import com.charity_org.demo.Models.Service.*;
//import com.charity_org.demo.Models.User;
//import com.charity_org.demo.Models.repository.EventRepository;
//import com.charity_org.demo.Models.repository.UserRepository;
//import com.charity_org.demo.Views.UserDisplays;
//import jakarta.persistence.Column;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//
//@RestController
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserDisplays userDisplays;
//
//    @GetMapping("/get-count")
//    public void showcount(){
//          userDisplays.showCount(userService.getCount());
//    }
//
//
//
//}
