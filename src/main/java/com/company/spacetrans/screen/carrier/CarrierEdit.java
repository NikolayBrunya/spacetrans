package com.company.spacetrans.screen.carrier;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Carrier;

@UiController("st_Carrier.edit")
@UiDescriptor("carrier-edit.xml")
@EditedEntityContainer("carrierDc")
public class CarrierEdit extends StandardEditor<Carrier> {
}