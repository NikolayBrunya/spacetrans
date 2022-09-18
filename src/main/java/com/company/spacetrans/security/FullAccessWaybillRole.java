package com.company.spacetrans.security;

import com.company.spacetrans.entity.Waybill;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "FullAccessWaybill", code = "full-access-waybill")
public interface FullAccessWaybillRole {

    @JpqlRowLevelPolicy(entityClass = Waybill.class, where = "")
    void accessWaybill();

}