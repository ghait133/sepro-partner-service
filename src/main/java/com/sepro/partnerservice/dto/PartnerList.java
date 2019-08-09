package com.sepro.partnerservice.dto;

import com.sepro.partnerservice.entity.Partner;
import java.util.List;

public class PartnerList {
    List<Partner> partners ;

    public PartnerList(List<Partner> partners) {
        this.partners = partners;
    }

    public PartnerList() {
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }
}
