package com.company.spacetrans.services;

import com.company.spacetrans.entity.Dimensions;
import com.company.spacetrans.entity.Waybill;
import com.company.spacetrans.entity.WaybillItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WaybillTest extends SpaceTransBaseTest{

    @Autowired
    WaybillItemService waybillItemService;

    @Test
    public void checkTotals()
    {

        //todo непонятно почему не работает тест - вопросы в блоке 'действие'
        //----------------------------------------------------------
        //region Дано
        var waybill = dataManager.create(Waybill.class);
        var waybillId = waybill.getId();
        Double var1 = 1.0;
        String name1 = "Item 1";
        Double var2 = 2.0;
        String name2 = "Item 2";
        //----------------------------------------------------------
        //region Item 1
        var item1 = dataManager.create(WaybillItem.class);
        item1.setName(name1);
        item1.setWeight( var1);
        item1.setWaybill(waybill);
        //Dim
        var dim1 = dataManager.create(Dimensions.class);
        dim1.setHeight(var1);
        dim1.setLenght(var1);
        dim1.setWidth(var1);
        item1.setDim( dim1);
        waybillItemService.calcCharge(item1); // нет в WaybillItemEventListener явный вызов

        //endregion Item 1
        //----------------------------------------------------------
        //region Item 2
        var item2 = dataManager.create(WaybillItem.class);
        item2.setName(name2);
        item2.setWeight( var2);
        item2.setWaybill(waybill);
        //Dim
        var dim2 = dataManager.create(Dimensions.class);
        dim2.setHeight(var2);
        dim2.setLenght(var2);
        dim2.setWidth(var2);
        item2.setDim( dim2);
        waybillItemService.calcCharge(item2); // нет в WaybillItemEventListener явный вызов
        //endregion Item 2
        //----------------------------------------------------------
        //endregion Дано
        //----------------------------------------------------------

        // непонятно надо в основной объект подкидывать связанные объекты или нет?
        waybill.setItems(List.of(item1, item2));
        // Действие

        //region Вот так вылетает при сохранении
//       dataManager.save(waybill);
//       dataManager.save(item1, item2);
        //endregion Вот так вылетает при сохранении

        // просто вот сохранить ведомость тоже не получается
        // похоже он не строит какое то дерево зависимостей для сохранения
        // During synchronization a new object was found through a relationship that was not marked cascade PERSIST
       // dataManager.save(waybill);

        // а вот так не считает почему то?
        // вроде событие подсчета есть на сущности (WaybillItemEventListener)
        // он не вызывает WaybillItemEventListener.onWaybillItemChangedBeforeCommit?
        // как правильно?
        dataManager.save(waybill, item1, item2);

       // Проверка
        var check = dataManager.load(Waybill.class).id(waybillId).one();

        Assertions.assertEquals(3, check.getTotalWeight());

    }

}
