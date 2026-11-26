package com.smarthrms.repository;

import com.smarthrms.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findByApplication_Id(Long applicationId);

    boolean existsByApplication_Id(Long applicationId);
}
