package com.smarthrms.dto;

import com.smarthrms.enums.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        Role role
) {}
