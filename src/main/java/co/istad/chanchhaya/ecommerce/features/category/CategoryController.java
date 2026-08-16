package co.istad.chanchhaya.ecommerce.features.category;

import co.istad.chanchhaya.ecommerce.features.category.dto.CategoryResponse;
import co.istad.chanchhaya.ecommerce.features.category.dto.CreateCategoryRequest;
import co.istad.chanchhaya.ecommerce.features.category.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/search")
    public List<CategoryResponse> search(@RequestParam(required = false, defaultValue = "") String name) {
        return categoryService.search(name);
    }


    @GetMapping("/{id}")
    public CategoryResponse findById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        categoryService.deleteById(id);
    }


    @PutMapping("/{id}")
    public CategoryResponse updateById(@PathVariable Integer id,
                                       @Valid @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return categoryService.updateById(id, updateCategoryRequest);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse createNew(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return categoryService.createNew(createCategoryRequest);
    }

    @GetMapping
    public Page<CategoryResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortById);
        return categoryService.findAll(pageable);
    }

}
