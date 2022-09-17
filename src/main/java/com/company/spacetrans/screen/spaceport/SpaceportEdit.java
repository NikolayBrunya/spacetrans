package com.company.spacetrans.screen.spaceport;

import com.company.spacetrans.entity.Moon;
import com.company.spacetrans.entity.Planet;
import com.company.spacetrans.services.NotificationService;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Spaceport;
import org.apache.commons.collections4.Factory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("st_Spaceport.edit")
@UiDescriptor("spaceport-edit.xml")
@EditedEntityContainer("spaceportDc")
public class SpaceportEdit extends StandardEditor<Spaceport> {

    @Autowired
    private EntityPicker<Planet> planetField;

    @Autowired
    private Notifications notifications;
    @Autowired
    private EntityPicker<Moon> moonField;
    @Autowired
    private InstanceContainer<Spaceport> spaceportDc;

    @Subscribe(id = "spaceportDc", target = Target.DATA_CONTAINER)
    public void onSpaceportDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Spaceport> event) {
//        if (event.getProperty().equals("isDefault")) {
//            notifications.create().withCaption("Event")
//                    .withDescription("Default flag changes")
//                    .withType(Notifications.NotificationType.TRAY)
//                    .show();
//
//        }
    }

    @Subscribe
    public void onAfterClose(AfterCloseEvent event) {
//        CloseAction closeAction = event.getCloseAction();
//        notifications.create().withCaption("Event")
//                .withDescription("close" + closeAction)
//                .withType(Notifications.NotificationType.TRAY)
//                .show();
    }


    @Subscribe("planetField")
    public void onPlanetFieldValueChange(HasValue.ValueChangeEvent<Planet> event) {
        var check = planetField.getValue();
        if (check == null) {
            moonField.setEditable(true);
            return;
        }
        if (check.equals("") == false) {
            moonField.setEditable(false);
        } else  moonField.setEditable(true);
    }

    @Subscribe("moonField")
    public void onMoonFieldValueChange(HasValue.ValueChangeEvent<Moon> event) {
        var check = moonField.getValue();
        if (check == null) {
            planetField.setEditable(true);
            return;
        }
        if (check.equals("") == false) {
            planetField.setEditable(false);
        } else  planetField.setEditable(true);
    }

}