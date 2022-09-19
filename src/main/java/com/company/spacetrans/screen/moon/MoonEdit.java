package com.company.spacetrans.screen.moon;

import com.company.spacetrans.entity.AstronomicalBody;
import com.company.spacetrans.entity.Planet;
import io.jmix.ui.component.BrowserFrame;
import io.jmix.ui.component.FileStorageResource;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Moon;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("st_Moon.edit")
@UiDescriptor("moon-edit.xml")
@EditedEntityContainer("moonDc")
public class MoonEdit extends StandardEditor<Moon> {

    @Autowired
    private BrowserFrame pictureBrowserFrame;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        setPictureToFrame();
    }

    @Subscribe(id = "moonDc", target = Target.DATA_CONTAINER)
    public void onMoonDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Moon> event) {
        if ("picture".equals(event.getProperty())){
            setPictureToFrame();
        }
    }

    private void setPictureToFrame()
    {
        Moon planet = getEditedEntity();
        if (planet.getPicture() != null){
            pictureBrowserFrame.setSource(FileStorageResource.class)
                    .setFileReference(planet.getPicture())
                    .setMimeType(planet.getPicture().getContentType());
        }
    }
}