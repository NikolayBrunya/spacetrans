package com.company.spacetrans.screen.atmosphere;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Atmosphere;

@UiController("st_Atmosphere.browse")
@UiDescriptor("atmosphere-browse.xml")
@LookupComponent("atmospheresTable")
public class AtmosphereBrowse extends StandardLookup<Atmosphere> {
}