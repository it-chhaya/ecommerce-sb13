package co.istad.chanchhaya.ecommerce.features.category;

import co.istad.chanchhaya.ecommerce.features.category.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.chanchhaya.ecommerce.features.category.dto.UpdateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(UpdateCategoryRequest dto);

    void toEntity(UpdateCategoryRequest dto, @MappingTarget Category category);

    // What is Source? => Parameter
    // What is Target? => Return
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest);

    @Mapping(source = "name", target = "cateName")
    CategoryResponse mapCategoryToCategoryResponse(Category category);

}
