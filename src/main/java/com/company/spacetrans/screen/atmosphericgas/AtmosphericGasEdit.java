package com.company.spacetrans.screen.atmosphericgas;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.AtmosphericGas;

@UiController("st_AtmosphericGas.edit")
@UiDescriptor("atmospheric-gas-edit.xml")
@EditedEntityContainer("atmosphericGasDc")
public class AtmosphericGasEdit extends StandardEditor<AtmosphericGas> {
}