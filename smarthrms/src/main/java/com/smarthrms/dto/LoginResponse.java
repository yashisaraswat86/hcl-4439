package com.smarthrms.dto;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long expiresIn
) {}
