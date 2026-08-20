package com.javasamples.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskRequest(@NotBlank(message = "title must not be blank") String title) {
}
