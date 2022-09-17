package com.company.spacetrans.services;

import com.company.spacetrans.entity.Discounts;
import com.company.spacetrans.entity.Waybill;
import com.company.spacetrans.entity.WaybillItem;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Component
public class WaybillService {


    @Autowired
    private DataManager dataManager;

    public double calcTotalWeight(Waybill waybill){
       var i = waybill.getItems()
                             .stream()
                             .mapToDouble(item -> item.getWeight() == null ? 0 : item.getWeight())
                             .sum();
       waybill.setTotalWeight(i);
       return  i;
    }

    public void calcTotalCharge(@NotNull Waybill waybill) {

        // Считаем стоимость всех итемов
        var sum = waybill.getItems()
                .stream()
                .map(WaybillItem::getCharge)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // ищем скидку клиета
        if (waybill.getShipper() != null) {
            var grade = waybill.getShipper().getGrade();
            if (grade != null) {
                var coeff = dataManager.load(Discounts.class)
                        .query("select d from st_Discounts d where d.grade = :grade")
                        .parameter("grade", grade)
                        .optional()
                        .orElse(null);
                if (coeff != null) {
                    sum = sum.multiply(coeff.getValue());
                }
            }
        }
        waybill.setTotalCharge(sum);
    }

}
