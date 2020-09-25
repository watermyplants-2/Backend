package com.buildweekjava.watermyplant2.services;

import com.buildweekjava.watermyplant2.models.Plant;
import com.buildweekjava.watermyplant2.repository.PlantRepository;
import com.buildweekjava.watermyplant2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "plantService")
public class PlantServiceImpl implements PlantService {
    @Autowired
    PlantRepository plantRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Transactional
    @Override
    public List<Plant> findAll(){
        List<Plant> list = new ArrayList<>();
        plantRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
    public Plant update(Plant plant, long plantid) {
        Plant currentPlant = plantRepository.findById(plantid).orElseThrow(()->
                new EntityNotFoundException(Long.toString(plantid)));

        if (plant.getSpecies() != null) {
            currentPlant.setSpecies(plant.getSpecies());
        }
//        if (plant.getNickname() != null) {
//            currentPlant.setNickname(plant.getNickname());
//        }
//        if (plant.getH2oFrequency() != null) {
//            currentPlant.setH2oFrequency(plant.getH2oFrequency());
//        }

        return plantRepository.save(currentPlant);
    }

    @Override
    public Plant save(Plant plant,
                      long userid){
        Plant addPlant = new Plant();

        addPlant.setNickname(plant.getNickname());
        addPlant.setUser(userService.findUserById(userid));
        return plantRepository.save(addPlant);
    }

}
