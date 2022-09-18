package com.company.spacetrans.security;

import com.company.spacetrans.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "OrdinaryUser", code = "ordinary-user")
public interface OrdinaryUserRole {
    @MenuPolicy(menuIds = {"st_Planet.browse", "st_Moon.browse", "st_Gas.browse", "st_Spaceport.browse", "themeSettingsScreen"})
    @ScreenPolicy(screenIds = {"st_Planet.browse", "st_Moon.browse", "st_Gas.browse", "st_Atmosphere.browse", "st_AtmosphericGas.browse", "st_Spaceport.browse", "st_Moon.edit", "st_Planet.edit", "st_Spaceport.edit", "themeSettingsScreen", "st_Atmosphere.edit", "st_AtmosphericGas.edit", "st_Gas.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = Atmosphere.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Atmosphere.class, actions = EntityPolicyAction.READ)
    void atmosphere();

    @EntityAttributePolicy(entityClass = AstronomicalBody.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = AstronomicalBody.class, actions = EntityPolicyAction.READ)
    void astronomicalBody();

    @EntityAttributePolicy(entityClass = AtmosphericGas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = AtmosphericGas.class, actions = EntityPolicyAction.READ)
    void atmosphericGas();

    @EntityAttributePolicy(entityClass = Gas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Gas.class, actions = EntityPolicyAction.READ)
    void gas();

    @EntityAttributePolicy(entityClass = Planet.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Planet.class, actions = EntityPolicyAction.READ)
    void planet();

    @EntityAttributePolicy(entityClass = Moon.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Moon.class, actions = EntityPolicyAction.READ)
    void moon();

    @EntityAttributePolicy(entityClass = Spaceport.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Spaceport.class, actions = EntityPolicyAction.READ)
    void spaceport();
}