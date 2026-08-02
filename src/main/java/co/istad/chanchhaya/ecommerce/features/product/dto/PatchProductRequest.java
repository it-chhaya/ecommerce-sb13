package co.istad.chanchhaya.ecommerce.features.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record PatchProductRequest(
        String name,
        @Min(0)
        Integer qty,
        @Min(0)
        BigDecimal unitPrice,
        String description,
        Boolean isAvailable,
        @Positive
        Integer categoryId,
        List<@NotNull(message = "Tag ID cannot be null") Integer> tagIds
) {
}
