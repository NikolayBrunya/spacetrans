package com.company.spacetrans.listener;

import com.company.spacetrans.entity.User;
import com.company.spacetrans.entity.Waybill;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("st_WaybillEventListener")
public class WaybillEventListener {


    @Autowired
    private DataManager dataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @EventListener
    public void onWaybillChangedBeforeCommit(EntityChangedEvent<Waybill> event) {
        Waybill waybill;
        if (event.getType() != EntityChangedEvent.Type.DELETED) {
            waybill = dataManager.load(event.getEntityId()).one();
            waybill.setCreator((User) currentAuthentication.getUser());
            dataManager.save(waybill); // почему здесь нужно явно сохранять?????
        }

    }

//    @TransactionalEventListener
//    public void onWaybillChangedAfterCommit(EntityChangedEvent<Waybill> event) {
//
//    }
//    @EventListener
//    public void onWaybillLoading(EntityLoadingEvent<Waybill> event) {
//
//    }
//
//    @EventListener
//    public void onWaybillSaving(EntitySavingEvent<Waybill> event) {
//
//    }
}