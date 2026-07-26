package co.istad.chanchhaya.ecommerce.service.impl;

import co.istad.chanchhaya.ecommerce.dto.CreateProductRequest;
import co.istad.chanchhaya.ecommerce.dto.PatchProductRequest;
import co.istad.chanchhaya.ecommerce.dto.ProductResponse;
import co.istad.chanchhaya.ecommerce.entity.Category;
import co.istad.chanchhaya.ecommerce.entity.Product;
import co.istad.chanchhaya.ecommerce.entity.Tag;
import co.istad.chanchhaya.ecommerce.mapper.ProductMapper;
import co.istad.chanchhaya.ecommerce.repository.CategoryRepository;
import co.istad.chanchhaya.ecommerce.repository.ProductRepository;
import co.istad.chanchhaya.ecommerce.repository.TagRepository;
import co.istad.chanchhaya.ecommerce.service.ProductService;
import co.istad.chanchhaya.ecommerce.utils.GenerateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductResponse patchById(Integer id, PatchProductRequest patchProductRequest) {
        // TODO
        // Validate product ID
        Product validProduct = productRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product has not been found"));

        // Validate category ID if client patch
        if (patchProductRequest.categoryId() != null) {
            Category validCategory = categoryRepository
                    .findById(patchProductRequest.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Category has not been found"
                    ));
            validProduct.setCategory(validCategory);
        }

        // Validate tag IDs if client patch
        if (patchProductRequest.tagIds() != null) {
            List<Tag> validTags = patchProductRequest.tagIds()
                    .stream()
                    .map(tagId -> tagRepository.findById(tagId)
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Tag ID = " + tagId + "has not been found"
                            )))
                    .toList();
            validProduct.setTags(validTags);
        }

        productMapper.toEntity(patchProductRequest, validProduct);
        productRepository.save(validProduct);

        return productMapper.toProductResponse(validProduct);
    }


    @Override
    public Page<ProductResponse> findAll(int pageNumber, int pageSize) {
        Sort sortByIdDesc = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortByIdDesc);
        return productRepository
                .findAll(pageable)
                .map(productMapper::toProductResponse);
    }


    @Override
    public void createNew(CreateProductRequest createProductRequest) {
        // TODO
        // Validate category ID
        Category validCategory = categoryRepository
                .findById(createProductRequest.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category has not been found"
                ));

        // Validate tag IDs
        List<Tag> validTags = createProductRequest.tagIds()
                .stream()
                .map(tagId -> tagRepository.findById(tagId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tag ID = " + tagId + "has not been found"
                        )))
                .toList();

        Product newProduct = productMapper.toEntity(createProductRequest);
        newProduct.setCode(GenerateUtils.getProductCode()); // SB13-SKU-000001
        newProduct.setSlug(GenerateUtils.toSlug(newProduct.getName())); // ice-latte, angkor-beer
        newProduct.setCategory(validCategory);
        newProduct.setTags(validTags);
        newProduct.setIsDeleted(false);
        productRepository.save(newProduct);
    }

}
