package com.company.spacetrans.screen.waybill;

import com.company.spacetrans.entity.*;
import com.company.spacetrans.screen.carrier.CarrierBrowse;
import com.company.spacetrans.services.SpaceportService;
import com.company.spacetrans.services.WaybillItemService;
import com.company.spacetrans.services.WaybillService;
import io.jmix.core.DataManager;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.entitypicker.EntityLookupAction;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionChangeType;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.apache.commons.compress.archivers.sevenz.CLI;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.inject.Named;
import java.util.*;

import static liquibase.repackaged.org.apache.commons.lang3.BooleanUtils.and;

@UiController("st_Waybill.edit")
@UiDescriptor("waybill-edit.xml")
@EditedEntityContainer("waybillDc")
public class WaybillEdit extends StandardEditor<Waybill> {

//region include component
    @Named("consigneeField.entityLookup")
    private EntityLookupAction<Customer> consigneeFieldEntityLookup;

    @Autowired
    private WaybillService waybillService;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private CollectionContainer<Customer> individualDc;
    @Autowired
    private CollectionContainer<Customer> companyDc;
    @Autowired
    private EntityComboBox<Customer> shipperField;

    @Autowired
    DataManager dataManager;
    @Autowired
    private CollectionPropertyContainer<WaybillItem> itemsDc;
    @Autowired
    private WaybillItemService waybillItemService;
    @Autowired
    private EntityPicker<Carrier> carrierField;
    @Autowired
    private InstanceContainer<Waybill> waybillDc;
    @Autowired
    private ComboBox astroBodyDep;
    @Autowired
    private EntityPicker<Spaceport> departurePortField;
    @Autowired
    private EntityPicker<Spaceport> destinationPortField;
    @Autowired
    private SpaceportService spaceportService;
    @Autowired
    private ComboBox astroBodyDest;
    @Autowired
    private Table<WaybillItem> itemsTable;
//endregion include component
    @Subscribe
    public void onInit(InitEvent event) {
        consigneeFieldEntityLookup.setScreenId("st_Company.browse");
        shipperField.setOptionsContainer(companyDc);
        // set combo planet/moon
        var planetMoon = loadPlanetMoon();
        astroBodyDep.setOptionsMap(planetMoon);
        astroBodyDest.setOptionsMap(planetMoon);
    }

    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    public void onItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<WaybillItem> event) {


        // todo  почему не изменяется отображение на экране?
//        if (event.getChangeType() == CollectionChangeType.ADD_ITEMS ||
//            event.getChangeType() == CollectionChangeType.REMOVE_ITEMS ||
//            event.getChangeType() == CollectionChangeType.REFRESH)
//        {
//            var source = event.getSource();
//            if (source == null) return;
//            var item = source.getItems().stream().findAny().orElse(null);
//            if (item == null) return;
//            waybillService.calcTotalCharge(item.getWaybill());
//            waybillService.calcTotalWeight(item.getWaybill());
//        }

    }



    @Subscribe("checkConsigneeIndividual")
    public void onCheckConsigneeIndividualValueChange(HasValue.@NotNull ValueChangeEvent<Boolean> event) {
        // todo это может не сильно важно, но непонятно почему сначала caption, а потом галка в чекбоксе
        if (event.getValue()){
            consigneeFieldEntityLookup.setScreenId("st_Individual.browse");
        }else {
            consigneeFieldEntityLookup.setScreenId("st_Company.browse");
        }
    }

    @Subscribe("checkShipperIndividual")
    public void onCheckShipperIndividualValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (event.getValue()){
            shipperField.setOptionsContainer(individualDc);
        }else {
            shipperField.setOptionsContainer(companyDc);
        }
    }

    private Map<String, AstronomicalBody> loadPlanetMoon()
    {
        // Формируем список планет/лун
        Map<String, AstronomicalBody> pmMap = new LinkedHashMap<>();

        dataManager.load(Planet.class)
                .query("select p from st_Planet p")
                .list()
                .forEach( e -> pmMap.put("Planet:" + e.getName(), (AstronomicalBody) e));;


        dataManager.load(Moon.class)
                .query("select m from st_Moon m")
                .list()
                .forEach( e -> pmMap.put("Moon:" + e.getName(), (AstronomicalBody) e));

        return pmMap;

    }

    @Subscribe("carrierField.entityLookup")
    public void onCarrierFieldEntityLookup(Action.ActionPerformedEvent event) {

        // Ищем перевозчика с введеными портами
        Waybill waybill = waybillDc.getItem();

        var dest = waybill.getDeparturePort();
        var dep = waybill.getDestinationPort();

        CarrierBrowse carrierScreen = (CarrierBrowse) screenBuilders
                .lookup(Carrier.class, this)
                .withField(carrierField)
                .build();

        if ( ( dest != null) || ( dep != null) )
        {
            carrierScreen.setFilterPorts(waybill.getDeparturePort(),
                    waybill.getDestinationPort());

        } else return;

        carrierScreen.show();
    }

    @Subscribe("itemsTable.up")
    public void onItemsTableUp(Action.ActionPerformedEvent event) {
        //todo я вроде сортировку поставил по номеру, даже меняются номера элементов
        // но при этом сортировка не применяется
        waybillItemService.numberUp(itemsDc.getMutableItems(), itemsDc.getItem());
    }

    @Subscribe("itemsTable.down")
    public void onItemsTableDown(Action.ActionPerformedEvent event) {
        waybillItemService.numberDown(itemsDc.getMutableItems(), itemsDc.getItem());

    }

    @Subscribe("astroBodyDep")
    public void onAstroBodyDepValueChange(HasValue.ValueChangeEvent event) {
        // поиск порта по умолчанию
        if (event.getValue() instanceof AstronomicalBody) {
            Spaceport defSp = spaceportService.getDefaultSpacePort((AstronomicalBody)event.getValue());
            if (defSp != null) departurePortField.setValue(defSp);
            else departurePortField.clear();
        }
    }

    @Subscribe("astroBodyDest")
    public void onAstroBodyDestValueChange(HasValue.ValueChangeEvent event) {
        // поиск порта по умолчанию
        if (event.getValue() instanceof AstronomicalBody) {
            Spaceport defSp = spaceportService.getDefaultSpacePort((AstronomicalBody)event.getValue());
            if (defSp != null) destinationPortField.setValue(defSp);
            else destinationPortField.clear();
        }
    }

}