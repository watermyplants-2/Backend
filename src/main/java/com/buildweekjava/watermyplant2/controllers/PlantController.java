package com.buildweekjava.watermyplant2.controllers;

import com.buildweekjava.watermyplant2.models.Plant;
import com.buildweekjava.watermyplant2.services.PlantService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@JsonIgnoreProperties(value = "plantid")
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    PlantService plantService;

    @PostMapping(value = "/plant/{plantid}",
    consumes = {"application/json"},
    produces = {"application/json"})
    public ResponseEntity<?> addPlant(@RequestBody Plant addPlant,
                                      @PathVariable long plantid)
    {
        plantService.update(addPlant, plantid);
        return  new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/plant",
    produces = {"application/json"})
    public ResponseEntity<?> listAllPlants() {
        List<Plant> myPlants = plantService.findAll();
        return new ResponseEntity<>(myPlants, HttpStatus.OK);
    }

    @PutMapping(value = "/plant/{plantid}",
    consumes = {"application/json"})
    public ResponseEntity<?> updatePlants(@RequestBody Plant updatePlant,
                                          @PathVariable long plantid)
    {
        plantService.update(updatePlant, plantid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping(value = "/plant/{plantid}")
    public ResponseEntity<?> completePlants(@PathVariable long plantid)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/plant/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable long id)
    {
//    {plantService.delete();
    return new ResponseEntity<>(HttpStatus.OK);
    }

}

