package com.company.spacetrans.services;


import com.company.spacetrans.entity.Waybill;
import com.company.spacetrans.entity.WaybillItem;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WaybillItemService {


    public void calcCharge(WaybillItem item) {

        var height = item.getDim().getHeight() == null ? 1 : item.getDim().getHeight();
        var wight = item.getDim().getWidth() == null ? 1 : item.getDim().getWidth();
        var length = item.getDim().getLenght() == null ? 1 : item.getDim().getLenght();
        var weight = item.getDim().getWidth() == null ? 1 : item.getDim().getWidth();

        item.setCharge(BigDecimal.valueOf(height * wight * length * weight));
    }

    public void numberUp(List<WaybillItem> items, WaybillItem curItem) {
        changeNumber(items, curItem, -1);
    }

    public void numberDown(List<WaybillItem> items, WaybillItem curItem) {
        changeNumber(items, curItem, 1);
    }

    private void changeNumber(List<WaybillItem> items,
                              WaybillItem curItem,
                              Integer direction) {
        Integer oldNum = curItem.getNumber();
        Integer newNum = oldNum + direction;

        // ищем элемент с которым меняемся
        var swappedItem = items.stream()
                .filter(e -> e.getNumber().equals(newNum))
                .findAny()
                .orElse(null);
        if (swappedItem != null) {
            swappedItem.setNumber(oldNum);
            curItem.setNumber(newNum);
        }
    }

    public int getLastNumber(WaybillItem waybillItem) {
        var num = 0;
        var waybill = waybillItem.getWaybill();
        var items = waybill.getItems()
                .stream()
                .sorted(Comparator.comparing(WaybillItem::getNumber))
                .collect(Collectors.toList());
        if (items.size() > 0) {
            num = items.get(items.size() - 1).getNumber();
        }
        num++;
        return num;
    }


}
