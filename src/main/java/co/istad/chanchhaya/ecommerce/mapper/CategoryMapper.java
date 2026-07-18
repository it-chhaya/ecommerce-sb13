package co.istad.chanchhaya.ecommerce.mapper;

import co.istad.chanchhaya.ecommerce.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryResponse mapCategoryToCategoryResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .icon(category.getIcon())
                .description(category.getDescription())
                .parentCategory(mapParentCategoryToCategoryResponse(category.getParentCategory()))
                .build();
    }

    public CategoryResponse mapParentCategoryToCategoryResponse(Category category) {

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .icon(category.getIcon())
                .description(category.getDescription())
                .build();
    }

}
