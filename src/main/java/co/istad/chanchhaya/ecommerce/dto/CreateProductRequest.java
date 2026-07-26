package co.istad.chanchhaya.ecommerce.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Quantity is required")
        @Min(0)
        Integer qty,

        @NotNull(message = "Unit price is required")
        @Min(0)
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
