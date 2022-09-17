package com.company.spacetrans.screen.spaceport;

import io.jmix.ui.Notifications;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Spaceport;
import io.jmix.ui.sys.DialogsImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("st_Spaceport.browse")
@UiDescriptor("spaceport-browse.xml")
@LookupComponent("spaceportsTable")
public class SpaceportBrowse extends StandardLookup<Spaceport> {

    @Autowired
    private Notifications notifications;

    @Autowired
    private CollectionLoader<Spaceport> spaceportsDl;

    @Subscribe(id = "spaceportsDc", target = Target.DATA_CONTAINER)
    public void onSpaceportsDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Spaceport> event) {

//        var spaceport = event.getItem();
//
//            notifications.create().withCaption("Event")
//                    .withDescription("Property changed")
//                    .withType(Notifications.NotificationType.TRAY)
//                    .show();


    }

    @Subscribe(id = "spaceportsDc", target = Target.DATA_CONTAINER)
    public void onSpaceportsDcCollectionChange(CollectionContainer.CollectionChangeEvent<Spaceport> event) {
//        var spaceport = event.getChanges();
//        notifications.create().withCaption("Event")
//                .withDescription("Collection changes")
//                .withType(Notifications.NotificationType.TRAY)
//                .show();

    }

    void  refreshDl(Spaceport spaceport)
    {
        if(spaceport.getIsDefault()) spaceportsDl.load();
    }
    @Install(to = "spaceportsTable.create", subject = "afterCommitHandler")
    private void spaceportsTableCreateAfterCommitHandler(Spaceport spaceport) {
        refreshDl(spaceport);
    }

    @Install(to = "spaceportsTable.edit", subject = "afterCommitHandler")
    private void spaceportsTableEditAfterCommitHandler(Spaceport spaceport) {
        refreshDl(spaceport);
    }




}