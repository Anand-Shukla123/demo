package com.demo.controller;


import com.demo.entity.BusStop;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bus-stop")
public class BusStopController {

    @PostMapping
    public String getBusDetails(@RequestBody BusStop busStop) {


        return "Bus Stop Name";


    }
}
