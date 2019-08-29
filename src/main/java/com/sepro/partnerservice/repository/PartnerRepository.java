package com.sepro.partnerservice.repository;

import com.sepro.partnerservice.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface PartnerRepository extends JpaRepository<Partner,Long> {

    Partner findByEmail(String email);
}
