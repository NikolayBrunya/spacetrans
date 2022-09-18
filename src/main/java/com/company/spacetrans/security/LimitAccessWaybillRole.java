package com.company.spacetrans.security;

import com.company.spacetrans.entity.Waybill;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "LimitAccessWaybill", code = "limit-access-waybill")
public interface LimitAccessWaybillRole {

    @JpqlRowLevelPolicy(entityClass = Waybill.class,
                        where = "{E}.creator.id = :current_user_id")
    void accessWaybill();
}