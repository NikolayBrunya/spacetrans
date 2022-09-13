package com.company.spacetrans.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "ST_ATMOSPHERIC_GAS", indexes = {
        @Index(name = "IDX_ST_ATMOSPHERIC_GAS_GAS", columnList = "GAS_ID"),
        @Index(name = "IDX_STATMOSPHERICGA_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "st_AtmosphericGas")
public class AtmosphericGas {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @JoinColumn(name = "GAS_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Gas gas;

    @NumberFormat(pattern = "#,##0.##", decimalSeparator = ".", groupingSeparator = ",")
    @Column(name = "VOLUME", nullable = false)
    @NotNull
    private Double volume;

    @JoinColumn(name = "ATMOSPHERE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}