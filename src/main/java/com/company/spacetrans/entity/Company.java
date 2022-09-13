package com.company.spacetrans.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@JmixEntity
@Table(name = "ST_COMPANY")
@Entity(name = "st_Company")
@PrimaryKeyJoinColumn(name = "COMPANY_ID", referencedColumnName = "ID")
public class Company extends Customer {
    @Column(name = "REGISTRATION_ID")
    private String registrationId;

    @Column(name = "COMPANY_TYPE")
    private String companyType;

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}