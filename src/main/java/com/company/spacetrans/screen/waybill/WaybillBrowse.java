package com.company.spacetrans.screen.waybill;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Waybill;

@UiController("st_Waybill.browse")
@UiDescriptor("waybill-browse.xml")
@LookupComponent("waybillsTable")
public class WaybillBrowse extends StandardLookup<Waybill> {


}