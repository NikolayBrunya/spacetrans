package com.company.spacetrans.screen.planet;

import io.jmix.ui.component.BrowserFrame;
import io.jmix.ui.component.FileStorageResource;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Planet;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("st_Planet.edit")
@UiDescriptor("planet-edit.xml")
@EditedEntityContainer("planetDc")
public class PlanetEdit extends StandardEditor<Planet> {

    @Autowired
    private BrowserFrame pictureBrowserFrame;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        setPictureToFrame();
    }

    @Subscribe(id = "planetDc", target = Target.DATA_CONTAINER)
    public void onPlanetDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Planet> event) {
        if ("picture".equals(event.getProperty())){
            setPictureToFrame();
        }
    }

    
    
    private void setPictureToFrame()
    {
        Planet planet = getEditedEntity();
        if (planet.getPicture() != null){
            pictureBrowserFrame.setSource(FileStorageResource.class)
                    .setFileReference(planet.getPicture())
                    .setMimeType(planet.getPicture().getContentType());
        }
    }
}