package com.company.spacetrans.screen.discounts;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Discounts;

@UiController("st_Discounts.edit")
@UiDescriptor("discounts-edit.xml")
@EditedEntityContainer("discountsDc")
public class DiscountsEdit extends StandardEditor<Discounts> {
}