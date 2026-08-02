package co.istad.chanchhaya.ecommerce.features.product.dto;

import co.istad.chanchhaya.ecommerce.features.category.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.features.tag.dto.TagResponse;
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
