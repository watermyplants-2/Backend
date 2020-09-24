package com.buildweekjava.watermyplant2.controllers;

import com.buildweekjava.watermyplant2.services.PlantService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@JsonIgnoreProperties(value = "plantid")
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    PlantService plantService;

    @PostMapping(value = "/plant/{plantid}",
    consumes = {"application/json"},
    produces = "application/json"})


}
