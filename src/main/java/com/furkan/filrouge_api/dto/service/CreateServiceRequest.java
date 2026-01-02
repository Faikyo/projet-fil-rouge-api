package com.furkan.filrouge_api.dto.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateServiceRequest(
        @NotBlank String name,
        @NotNull @DecimalMin(value = "0.01") BigDecimal price
) {}

