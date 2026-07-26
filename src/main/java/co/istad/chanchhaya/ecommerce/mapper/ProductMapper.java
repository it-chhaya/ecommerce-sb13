package co.istad.chanchhaya.ecommerce.mapper;

import co.istad.chanchhaya.ecommerce.dto.CreateProductRequest;
import co.istad.chanchhaya.ecommerce.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(CreateProductRequest createProductRequest);

}
