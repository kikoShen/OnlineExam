package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.User;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/student")
public class UserController {
   @Autowired
    private UserService userService;
   
   @ModelAttribute
    public User getUser(){
       return new User();
   }
   
   @RequestMapping("/login")
   public JSONObject Login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
       boolean bool=userService.checkUser(user.getEmail(),user.getPassword());
       JSONObject jsonObject =new JSONObject();
       jsonObject.put("result",bool);
       return jsonObject;
   }

   @RequestMapping("/register")
   public JSONObject Register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
       boolean bool =userService.Register(user.getEmail(),user.getPassword());
       JSONObject jsonObject =new JSONObject();
       jsonObject.put("result",bool);
       return jsonObject;
   }


}
