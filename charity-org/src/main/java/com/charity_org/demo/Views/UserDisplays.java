package com.charity_org.demo.Views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserDisplays {



    public void showCount(Long count){
        System.out.println("the number of current users: "+count);
    }
}
