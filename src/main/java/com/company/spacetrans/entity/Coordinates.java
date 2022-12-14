package com.company.spacetrans.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.PositiveOrZero;

@JmixEntity(name = "st_Coordinates")
@Embeddable
public class Coordinates {
    @NumberFormat(pattern = "#,##0.######", decimalSeparator = ".", groupingSeparator = ",")
    @PositiveOrZero(message = "{msg://com.company.spacetrans.entity/Coordinates.latitude.validation.PositiveOrZero}")
    @Column(name = "LATITUDE")
    private Double latitude;

    @NumberFormat(pattern = "#,##0.######", decimalSeparator = ".", groupingSeparator = ",")
    @PositiveOrZero(message = "{msg://com.company.spacetrans.entity/Coordinates.longitude.validation.PositiveOrZero}")
    @Column(name = "LONGITUDE")
    private Double longitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}