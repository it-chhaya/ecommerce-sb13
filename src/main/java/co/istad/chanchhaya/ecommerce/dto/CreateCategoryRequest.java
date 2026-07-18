package co.istad.chanchhaya.ecommerce.dto;

public record CreateCategoryRequest(
        String name,
        String description,
        String icon,
        Integer parentCategoryId
) {
}
