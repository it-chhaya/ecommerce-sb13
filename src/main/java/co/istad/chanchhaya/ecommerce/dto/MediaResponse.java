package co.istad.chanchhaya.ecommerce.dto;

import lombok.Builder;

@Builder
public record MediaResponse(
        Integer id,
        String name,
        String extension,
        String mediaType,
        Float size, // convert from BYTE to MB
        String measurement // MB
) {
}
