package com.company.spacetrans.screen.waybillitem;

import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.WaybillItem;

@UiController("st_WaybillItem.browse")
@UiDescriptor("waybill-item-browse.xml")
@LookupComponent("waybillItemsTable")
public class WaybillItemBrowse extends StandardLookup<WaybillItem> {
}