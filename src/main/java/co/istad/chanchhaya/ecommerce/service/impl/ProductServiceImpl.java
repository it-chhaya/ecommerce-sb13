package co.istad.chanchhaya.ecommerce.service.impl;

import co.istad.chanchhaya.ecommerce.dto.CreateProductRequest;
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
