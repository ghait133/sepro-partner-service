package com.sepro.partnerservice.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseIdEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
