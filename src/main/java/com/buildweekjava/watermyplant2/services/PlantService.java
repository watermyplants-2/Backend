package com.buildweekjava.watermyplant2.services;

import com.buildweekjava.watermyplant2.models.Plant;
import java.util.List;

public interface PlantService {
    List<Plant> findAll();

    Plant update(Plant plant,
                 long plantid);
    Plant save(Plant plant,
               long userid);
}
