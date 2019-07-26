package com.sepro.partnerservice.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "partner")
public class Partner extends BaseIdEntity{


    String companyName;
    String email;
    String telNumbre;

    @OneToOne(targetEntity = Adresse.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "adresse_id", foreignKey = @ForeignKey(name = "ID"))
    private Adresse adresse;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelNumbre() {
        return telNumbre;
    }

    public void setTelNumbre(String telNumbre) {
        this.telNumbre = telNumbre;
    }
}
