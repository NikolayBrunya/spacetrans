package com.company.spacetrans.listener;

import com.company.spacetrans.entity.AstronomicalBody;
import com.company.spacetrans.entity.Spaceport;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("st_SpaceportEventListener")
public class SpaceportEventListener {


    @Autowired
    DataManager dataManager;

    @EventListener
    public void onSpaceportLoading(EntityLoadingEvent<Spaceport> event) {

    }


    @EventListener
    public void onSpaceportChangedBeforeCommit(EntityChangedEvent<Spaceport> event) {

        if( event.getType() == EntityChangedEvent.Type.DELETED ) return;

        var spaceport = dataManager.load(event.getEntityId()).one();
        if (spaceport.getIsDefault()) {
            //если поставили флаг по умолчанию
            // необходимо убрать флаги с других портов на астрономическом объекте
            var spaceports = dataManager.load(Spaceport.class)
                    .query("select s from st_Spaceport s " +
                            "where s.isDefault = true and " +
                            "( " +
                            "(s.moon is not null  and s.moon = :moon) or" +
                            "(s.planet is not null  and s.planet = :planet)" +
                            ")" +
                            "and s.id <> :id")
                    .parameter("moon", spaceport.getMoon())
                    .parameter("planet", spaceport.getPlanet())
                    .parameter("id", spaceport.getId())
                    .list();
            spaceports.stream().forEach(s->s.setIsDefault(false));
            dataManager.save(spaceports.toArray());
        }
    }

    @TransactionalEventListener
    public void onSpaceportChangedAfterCommit(EntityChangedEvent<Spaceport> event) {
    }



    @EventListener
    public void onSpaceportSaving(EntitySavingEvent<Spaceport> event) {

//        var spaceport = event.getEntity();
//        if (spaceport.getIsDefault()) {
//            //если поставили флаг по умолчанию
//            // необходимо убрать флаги с других портов на астрономическом объекте
//            var spaceports = dataManager.load(Spaceport.class)
//                    .query("select s from st_Spaceport s " +
//                    "where s.isDefault = true and " +
//                            "( " +
//                            "(s.moon is not null  and s.moon = :moon) or" +
//                            "(s.planet is not null  and s.planet = :planet)" +
//                            ")" +
//                            "and s.id <> :id")
//                    .parameter("moon", spaceport.getMoon())
//                    .parameter("planet", spaceport.getPlanet())
//                    .parameter("id", spaceport.getId())
//                    .list();
//            spaceports.stream().forEach(s->s.setIsDefault(false));
//            dataManager.save(spaceports.toArray());
//        }
    }
}