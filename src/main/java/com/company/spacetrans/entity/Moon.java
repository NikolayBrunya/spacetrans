package com.company.spacetrans.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JmixEntity
@Table(name = "ST_MOON", indexes = {
        @Index(name = "IDX_ST_MOON_ATMOSPHERE", columnList = "ATMOSPHERE_ID"),
        @Index(name = "IDX_ST_MOON_PLANET", columnList = "PLANET_ID")
})
@Entity(name = "st_Moon")
public class Moon extends AstronomicalBody {
    @JoinColumn(name = "ATMOSPHERE_ID", nullable = false)
    @Composition
    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Atmosphere atmosphere;

    @NotNull
    @JoinColumn(name = "PLANET_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Planet planet;

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }
}