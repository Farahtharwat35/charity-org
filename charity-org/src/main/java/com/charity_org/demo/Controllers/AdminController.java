package com.charity_org.demo.Controllers;

import com.charity_org.demo.Models.Service.RolesDecorator.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminController {
    @Autowired
    private AdminService adminService;



}
