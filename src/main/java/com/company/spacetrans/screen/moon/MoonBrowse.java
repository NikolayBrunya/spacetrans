package com.company.spacetrans.screen.moon;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Moon;

@UiController("st_Moon.browse")
@UiDescriptor("moon-browse.xml")
@LookupComponent("moonsTable")
public class MoonBrowse extends StandardLookup<Moon> {
}