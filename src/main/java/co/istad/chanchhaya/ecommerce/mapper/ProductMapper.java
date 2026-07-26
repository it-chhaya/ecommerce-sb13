package co.istad.chanchhaya.ecommerce.mapper;

import co.istad.chanchhaya.ecommerce.dto.CreateProductRequest;
import co.istad.chanchhaya.ecommerce.dto.PatchProductRequest;
import co.istad.chanchhaya.ecommerce.dto.ProductResponse;
import co.istad.chanchhaya.ecommerce.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        CategoryMapper.class
})
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(PatchProductRequest dto, @MappingTarget Product entity);

    ProductResponse toProductResponse(Product product);

    Product toEntity(CreateProductRequest createProductRequest);

}
