package co.istad.chanchhaya.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String icon,
        String description,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        CategoryResponse parentCategory
) {
}
