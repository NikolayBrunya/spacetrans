package com.company.spacetrans.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@JmixEntity(name = "st_AstronomicalBody")
@MappedSuperclass
public class AstronomicalBody {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @Positive(message = "{msg://com.company.spacetrans.entity/AstronomicalBody.mass.validation.Positive}")
    @Column(name = "MASS", nullable = false)
    @NotNull
    private Double mass;

    @Column(name = "PICTURE")
    @Lob
    private FileRef picture;


    public FileRef getPicture() {
        return picture;
    }

    public void setPicture(FileRef picture) {
        this.picture = picture;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}