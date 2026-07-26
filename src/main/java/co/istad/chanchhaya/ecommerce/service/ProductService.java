package co.istad.chanchhaya.ecommerce.service;

import co.istad.chanchhaya.ecommerce.dto.CreateProductRequest;

public interface ProductService {

    /**
     * Create a new product
     */
    void createNew(CreateProductRequest createProductRequest);

}
