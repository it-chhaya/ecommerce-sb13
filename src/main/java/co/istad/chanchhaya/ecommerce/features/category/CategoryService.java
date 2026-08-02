package co.istad.chanchhaya.ecommerce.features.category;

import co.istad.chanchhaya.ecommerce.features.category.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.chanchhaya.ecommerce.features.category.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {


    CategoryResponse findById(Integer id);


    void deleteById(Integer id);


    /**
     * កែប្រែព័ត៌មាន category តាមរយៈ id
     * @param id គឺតំណាង category
     * @param updateCategoryRequest ព័ត៌មាន category ថ្មី
     * @return CategoryResponse
     */
    CategoryResponse updateById(Integer id, UpdateCategoryRequest updateCategoryRequest);


    /**
     * បង្កើត category ថ្មី
     * @param createCategoryRequest ព័ត៌មានសម្រាប់បង្កើត category ថ្មី
     * @return CategoryResponse
     */
    CategoryResponse createNew(CreateCategoryRequest createCategoryRequest);


    /**
     * ទាញព័ត៌មាន category តាមរយៈ pagination
     * @return សំណុំនៃ CategoryResponse
     */
    Page<CategoryResponse> findAll(Pageable pageable);

}
