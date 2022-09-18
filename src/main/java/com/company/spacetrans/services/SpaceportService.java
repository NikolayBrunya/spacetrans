package com.company.spacetrans.services;

import com.company.spacetrans.entity.AstronomicalBody;
import com.company.spacetrans.entity.Moon;
import com.company.spacetrans.entity.Planet;
import com.company.spacetrans.entity.Spaceport;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceportService {

    @Autowired
    private DataManager dataManager;

    public Spaceport getDefaultSpacePort(AstronomicalBody astroBody) {

        return dataManager.load(Spaceport.class)
                   .query("select sp from st_Spaceport sp where sp.isDefault = true " +
                           " and (" +
                           " (sp.planet is not null and sp.planet.id = :id)" +
                           " or " +
                           " ( sp.moon is not null and  sp.moon.id = :id )" +
                           ")" )
                   .parameter("id", astroBody.getId())
                   .optional().orElse(null);

    }
}
