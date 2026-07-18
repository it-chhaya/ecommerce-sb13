package co.istad.chanchhaya.ecommerce.service;

import co.istad.chanchhaya.ecommerce.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.dto.CreateCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {


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
