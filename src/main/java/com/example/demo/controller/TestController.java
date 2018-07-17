package com.example.demo.controller;

import com.example.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    PaperService paperService;




    @RequestMapping("/select")
    public String select(){
        return "hi ,this is exam system!";
    }




}
