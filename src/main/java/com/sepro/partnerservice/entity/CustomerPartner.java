package com.sepro.partnerservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer_partner")
public class CustomerPartner extends BaseIdEntity{

    Long customerId;
    Long partnerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }
}
