package com.company.spacetrans.screen.waybillitem;

import com.company.spacetrans.services.WaybillItemService;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.WaybillItem;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("st_WaybillItem.edit")
@UiDescriptor("waybill-item-edit.xml")
@EditedEntityContainer("waybillItemDc")
public class WaybillItemEdit extends StandardEditor<WaybillItem> {


    @Autowired
    private WaybillItemService waybillItemService;

    @Autowired
    private InstanceContainer<WaybillItem> waybillItemDc;

    @Subscribe
    public void onInit(InitEvent event) {

    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<WaybillItem> event) {

        var num = waybillItemService.getLastNumber(event.getEntity());
        event.getEntity().setNumber(num);
    }

    @Subscribe("dimLenghtField")
    public void onDimLenghtFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        calcCharge();
    }

    @Subscribe("dimWidthField")
    public void onDimWidthFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        calcCharge();
    }

    @Subscribe("dimHeightField")
    public void onDimHeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        calcCharge();
    }


    @Subscribe("weightField")
    public void onWeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        calcCharge();
    }

    private void calcCharge() {
        waybillItemService.calcCharge(waybillItemDc.getItem());
    }
}