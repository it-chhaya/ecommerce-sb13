package co.istad.chanchhaya.ecommerce.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductResponse(
        String code,
        String slug,
        String name,
        Integer qty,
        BigDecimal unitPrice,
        String thumbnail,
        String description,
        Boolean isAvailable,
        CategoryResponse category,
        List<TagResponse> tags
) {
}
