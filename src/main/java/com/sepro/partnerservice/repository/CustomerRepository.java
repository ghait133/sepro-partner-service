package com.sepro.partnerservice.repository;

import com.sepro.partnerservice.entity.CustomerPartner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerPartner,Long> {
    List<CustomerPartner> findByPartnerId(Long partnerId);

}
