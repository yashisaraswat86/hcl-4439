
package com.smarthrms.controller;

import com.smarthrms.dto.OfferRequest;
import com.smarthrms.dto.OfferResponse;
import com.smarthrms.dto.UpdateOfferStatusRequest;
import com.smarthrms.entity.Offer;
import com.smarthrms.service.OfferService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // Create an offer
    @PostMapping
    public ResponseEntity<OfferResponse> createOffer(
            @RequestParam Long applicationId,
            @Valid @RequestBody OfferRequest request) {

        Offer offer = offerService.createOffer(
                applicationId,
                request.offeredSalary(),
                request.joiningDate(),
                request.validUntil()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(convertToResponse(offer));
    }

    // Get offer by application ID
    @GetMapping("/application/{applicationId}")
    public ResponseEntity<OfferResponse> getOfferByApplication(
            @PathVariable Long applicationId) {

        Offer offer = offerService
                .getOfferByApplication(applicationId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Offer not found for this application"));

        return ResponseEntity.ok(convertToResponse(offer));
    }

    // Update offer status
    @PatchMapping("/{offerId}/status")
    public ResponseEntity<OfferResponse> updateOfferStatus(
            @PathVariable Long offerId,
            @Valid @RequestBody UpdateOfferStatusRequest request) {

        Offer offer = offerService.updateOfferStatus(
                offerId,
                request.status()
        );

        return ResponseEntity.ok(convertToResponse(offer));
    }

    // Convert entity into response DTO
    private OfferResponse convertToResponse(Offer offer) {

        return new OfferResponse(
                offer.getId(),
                offer.getApplication().getId(),
                offer.getApplication().getCandidate().getUser().getName(),
                offer.getApplication().getJob().getTitle(),
                offer.getOfferedSalary(),
                offer.getJoiningDate(),
                offer.getValidUntil(),
                offer.getStatus().name(),
                offer.getCreatedAt()
        );
    }
}

