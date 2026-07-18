package co.istad.chanchhaya.ecommerce.service.impl;

import co.istad.chanchhaya.ecommerce.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.dto.CreateCategoryRequest;
import co.istad.chanchhaya.ecommerce.entity.Category;
import co.istad.chanchhaya.ecommerce.mapper.CategoryMapper;
import co.istad.chanchhaya.ecommerce.repository.CategoryRepository;
import co.istad.chanchhaya.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryResponse createNew(CreateCategoryRequest createCategoryRequest) {
        // TODO:
        // 1. Validate all information form DTO
        // Validate category name (unique)
        Optional<Category> category = categoryRepository.findByName(createCategoryRequest.name());

        if (category.isPresent()) {
            System.out.println("Category already exists");
        }

        Category newCategory = categoryMapper
                .mapCreateCategoryRequestToCategory(createCategoryRequest);
        newCategory.setIsDeleted(false);

        // Validate parent category id
        if (createCategoryRequest.parentCategoryId() != null) {
            Category parentCategory = categoryRepository
                    .findById(createCategoryRequest.parentCategoryId())
                    .orElseThrow();
            newCategory.setParentCategory(parentCategory);
        }

        newCategory = categoryRepository.save(newCategory);

        return categoryMapper.mapCategoryToCategoryResponse(newCategory);
    }


    @Override
    public List<CategoryResponse> findAll() {
        // ទាញយកទិន្នន័យចេញពី database
        List<Category> categories = categoryRepository.findAll();
        // បំលែង entity ទៅជា DTO
        return categories.stream()
                .map(categoryMapper::mapCategoryToCategoryResponse)
                .toList();
    }

}
