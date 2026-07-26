package co.istad.chanchhaya.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Quantity is required")
        @Positive
        Integer qty,

        @NotNull(message = "Unit price is required")
        @Positive
        BigDecimal unitPrice,

        String thumbnail,
        String description,

        @NotNull(message = "Status availability is required")
        Boolean isAvailable,

        @NotNull(message = "Category ID is required")
        @Positive
        Integer categoryId,

        @NotEmpty(message = "Tag is required at least one")
        // null, [null, null]
        List<@NotNull(message = "Tag ID cannot be null") Integer> tagIds
) {
}
