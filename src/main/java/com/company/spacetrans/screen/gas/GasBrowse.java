package com.company.spacetrans.screen.gas;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Gas;

@UiController("st_Gas.browse")
@UiDescriptor("gas-browse.xml")
@LookupComponent("gasesTable")
public class GasBrowse extends StandardLookup<Gas> {
}