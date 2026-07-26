package co.istad.chanchhaya.ecommerce.service.impl;

import co.istad.chanchhaya.ecommerce.dto.CreateProductRequest;
import co.istad.chanchhaya.ecommerce.entity.Category;
import co.istad.chanchhaya.ecommerce.repository.CategoryRepository;
import co.istad.chanchhaya.ecommerce.repository.ProductRepository;
import co.istad.chanchhaya.ecommerce.repository.TagRepository;
import co.istad.chanchhaya.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

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
        boolean isValidTags = createProductRequest.tagIds()
                .stream()
                .allMatch(tagRepository::existsById);

        if (!isValidTags) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Tag has not been found");
        }
    }

}
