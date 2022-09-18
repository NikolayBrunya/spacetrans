package com.company.spacetrans.listener;

import com.company.spacetrans.entity.User;
import com.company.spacetrans.entity.Waybill;
import com.company.spacetrans.entity.WaybillItem;
import com.company.spacetrans.services.WaybillService;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityLoadingEvent;
import io.jmix.core.event.EntitySavingEvent;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component("st_WaybillItemEventListener")
public class WaybillItemEventListener {

    @Autowired
    CurrentAuthentication currentAuthentication;
    @Autowired
    private DataManager dataManager;

    @Autowired
    private WaybillService waybillService;


    @EventListener
    public void onWaybillItemChangedBeforeCommit(EntityChangedEvent<WaybillItem> event) {

        Waybill waybill;
        // При изменении списка
        // 1 - пересчитать итоговый вес в ведомости
        // 2 - пересчитать итоговую стоимость с учетом скидки клиента
        // 3 - перенумеровать позиции - при удалении
        if (event.getType() == EntityChangedEvent.Type.DELETED) {
            var itemId = event.getChanges().getOldReferenceId("waybill");
            waybill = (Waybill) dataManager.load(itemId).one();
            // 3 - перенумировать позиции
            waybillService.recalItemsNumber(waybill);
        } else {
            var item = dataManager.load(event.getEntityId()).one();
            waybill = item.getWaybill();
        }
        // 1 - пересчитать итоговый вес в ведомости
        waybillService.calcTotalWeight(waybill);
        // 2 - пересчитать итоговую стоимость
        waybillService.calcTotalCharge(waybill);
        // А вот здесь не нужно явно сохранять ведомость???
        //waybill.setCreator((User) currentAuthentication.getUser());
    }

//    @TransactionalEventListener
//    public void onWaybillItemChangedAfterCommit(EntityChangedEvent<WaybillItem> event) {
//
//    }
//
//    @EventListener
//    public void onWaybillItemLoading(EntityLoadingEvent<WaybillItem> event) {
//
//    }
//
//    @EventListener
//    public void onWaybillItemSaving(EntitySavingEvent<WaybillItem> event) {
//
//
//    }
}