package com.company.spacetrans.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Positive;

@JmixEntity(name = "st_Dimensions")
@Embeddable
public class Dimensions {
    @Positive(message = "{msg://com.company.spacetrans.entity/Dimensions.lenght.validation.Positive}")
    @Column(name = "LENGHT")
    private Double lenght;

    @Positive(message = "{msg://com.company.spacetrans.entity/Dimensions.width.validation.Positive}")
    @Column(name = "WIDTH")
    private Double width;

    @Positive(message = "{msg://com.company.spacetrans.entity/Dimensions.height.validation.Positive}")
    @Column(name = "HEIGHT")
    private Double height;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLenght() {
        return lenght;
    }

    public void setLenght(Double lenght) {
        this.lenght = lenght;
    }
}