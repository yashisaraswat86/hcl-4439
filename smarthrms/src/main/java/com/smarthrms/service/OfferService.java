
package com.smarthrms.service;

import com.smarthrms.entity.Application;
import com.smarthrms.entity.Offer;
import com.smarthrms.enums.ApplicationStatus;
import com.smarthrms.enums.OfferStatus;
import com.smarthrms.repository.ApplicationRepository;
import com.smarthrms.repository.OfferRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final ApplicationRepository applicationRepository;

    public OfferService(
            OfferRepository offerRepository,
            ApplicationRepository applicationRepository) {
        this.offerRepository = offerRepository;
        this.applicationRepository = applicationRepository;
    }

    // Create a new offer
    @Transactional
    public Offer createOffer(
            Long applicationId,
            BigDecimal offeredSalary,
            LocalDate joiningDate,
            LocalDate validUntil) {

        Application application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Application not found"));

        // Only selected candidates can receive offers
        if (application.getStatus() != ApplicationStatus.SELECTED) {
            throw new IllegalArgumentException(
                    "Only selected candidates can receive offers");
        }

        // Prevent duplicate offers
        if (offerRepository.existsByApplication_Id(applicationId)) {
            throw new IllegalArgumentException(
                    "An offer already exists for this application");
        }

        // Validate salary
        if (offeredSalary == null ||
                offeredSalary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Offered salary must be greater than zero");
        }

        // Validate joining date
        if (joiningDate == null ||
                !joiningDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Joining date must be in the future");
        }

        // Validate offer expiry
        if (validUntil == null ||
                !validUntil.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Offer validity date must be in the future");
        }

        if (!validUntil.isBefore(joiningDate)) {
            throw new IllegalArgumentException(
                    "Offer expiry date must be before joining date");
        }

        Offer offer = new Offer();
        offer.setApplication(application);
        offer.setOfferedSalary(offeredSalary);
        offer.setJoiningDate(joiningDate);
        offer.setValidUntil(validUntil);
        offer.setStatus(OfferStatus.OFFERED);

        return offerRepository.save(offer);
    }

    // Get offer using application ID
    public Optional<Offer> getOfferByApplication(Long applicationId) {

        if (!applicationRepository.existsById(applicationId)) {
            throw new EntityNotFoundException("Application not found");
        }

        return offerRepository.findByApplication_Id(applicationId);
    }

    // Update offer status
    @Transactional
    public Offer updateOfferStatus(Long offerId, OfferStatus newStatus) {

        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Offer not found"));

        if (newStatus == null) {
            throw new IllegalArgumentException(
                    "Offer status is required");
        }

        if (offer.getStatus() != OfferStatus.OFFERED) {
            throw new IllegalArgumentException(
                    "Only offered records can be updated");
        }

        if (newStatus == OfferStatus.OFFERED) {
            throw new IllegalArgumentException(
                    "Offer is already in offered status");
        }

        offer.setStatus(newStatus);

        // Accepted offer means the candidate is hired
        if (newStatus == OfferStatus.ACCEPTED) {
            Application application = offer.getApplication();
            application.setStatus(ApplicationStatus.HIRED);
        }

        return offerRepository.save(offer);
    }
}

