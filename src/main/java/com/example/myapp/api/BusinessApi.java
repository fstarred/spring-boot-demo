package com.example.myapp.api;

import com.example.myapp.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessApi {

    @Autowired
    BusinessService service;

    @GetMapping
    int getRandomNumber() {
        return service.aggregateRandomNumbers();
    }

}
