package com.company.spacetrans.screen.atmosphere;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Atmosphere;

@UiController("st_Atmosphere.edit")
@UiDescriptor("atmosphere-edit.xml")
@EditedEntityContainer("atmosphereDc")
public class AtmosphereEdit extends StandardEditor<Atmosphere> {
}