package co.istad.chanchhaya.ecommerce.mapper;

import co.istad.chanchhaya.ecommerce.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.dto.CreateCategoryRequest;
import co.istad.chanchhaya.ecommerce.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // What is Source? => Parameter
    // What is Target? => Return
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    @Mapping(source = "name", target = "cateName")
    CategoryResponse mapCategoryToCategoryResponse(Category category);

}
