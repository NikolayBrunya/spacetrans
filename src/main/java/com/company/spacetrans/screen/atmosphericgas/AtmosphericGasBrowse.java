package com.company.spacetrans.screen.atmosphericgas;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.AtmosphericGas;

@UiController("st_AtmosphericGas.browse")
@UiDescriptor("atmospheric-gas-browse.xml")
@LookupComponent("atmosphericGasesTable")
public class AtmosphericGasBrowse extends StandardLookup<AtmosphericGas> {
}