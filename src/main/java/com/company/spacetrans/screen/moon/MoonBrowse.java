package com.company.spacetrans.screen.moon;

import com.company.spacetrans.entity.Planet;
import io.jmix.core.DataManager;
import io.jmix.ui.component.FileStorageUploadField;
import io.jmix.ui.component.SingleFileUploadField;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Moon;
import io.jmix.ui.upload.TemporaryStorage;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UiController("st_Moon.browse")
@UiDescriptor("moon-browse.xml")
@LookupComponent("moonsTable")
public class MoonBrowse extends StandardLookup<Moon> {

    @Autowired
    private FileStorageUploadField uploadMoons;

    @Autowired
    private CollectionLoader<Moon> moonsDl;

    @Autowired
    private TemporaryStorage temporaryStorage;

    @Autowired
    private DataManager dataManager;
    @Subscribe("uploadMoons")
    public void onUploadMoonsFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) throws IOException {

        UUID fileId = uploadMoons.getFileId();
        File file = temporaryStorage.getFile(fileId);

        List<String> stringList = FileUtils.readLines(file, StandardCharsets.UTF_8);

        List<Moon> impPlanets = new ArrayList<>(stringList.size());

        for (var planetSt : stringList) {
            String[] split = planetSt.split(";", 2);

            if (split.length != 2) return; // какой то экспш или ошибку

            var planetName = split[0];
            var planetMass = Double.valueOf(split[1]);
            //это точно надо потом переделать, не дело лезть в базу для каждой записи
            var planet = dataManager.load(Moon.class)
                    .query("select p from st_Moon p where p.name = :name")
                    .parameter("name", planetName)
                    .optional().orElse(null);

            if (planet == null) {
                planet = dataManager.create(Moon.class);
                planet.setName(planetName);
            }

            planet.setMass(planetMass);

            //impPlanets.add(planet);//перед тем как добавлять нужно проверить может уже есть в локальном списке
            if (impPlanets.stream().filter(c -> c.getName().equals(planetName)).count() == 0) impPlanets.add(planet);

        }
        dataManager.save(impPlanets.toArray());
        moonsDl.load();

    }



}