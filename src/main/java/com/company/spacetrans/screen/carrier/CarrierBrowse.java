package com.company.spacetrans.screen.carrier;

import com.company.spacetrans.entity.Spaceport;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.spacetrans.entity.Carrier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@UiController("st_Carrier.browse")
@UiDescriptor("carrier-browse.xml")
@LookupComponent("carriersTable")
public class CarrierBrowse extends StandardLookup<Carrier> {

    @Autowired
    private CollectionLoader<Carrier> carriersDl;

    public void setFilterPorts(Spaceport portDep, Spaceport portDest)
    {
        carriersDl.setParameter("portDest", portDest);
        carriersDl.setParameter("portDep", portDep);
    }
    public void setPorts(List<Spaceport> ports)
    {
        carriersDl.setParameter("ports", ports);
    }
}