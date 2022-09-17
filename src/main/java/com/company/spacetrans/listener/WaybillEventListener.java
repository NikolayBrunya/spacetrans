package com.company.spacetrans.listener;

import com.company.spacetrans.entity.Waybill;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("st_WaybillEventListener")
public class WaybillEventListener {

    @EventListener
    public void onWaybillLoading(EntityLoadingEvent<Waybill> event) {

    }

    @EventListener
    public void onWaybillSaving(EntitySavingEvent<Waybill> event) {

    }

    @EventListener
    public void onWaybillChangedBeforeCommit(EntityChangedEvent<Waybill> event) {



    }

    @TransactionalEventListener
    public void onWaybillChangedAfterCommit(EntityChangedEvent<Waybill> event) {

    }
}