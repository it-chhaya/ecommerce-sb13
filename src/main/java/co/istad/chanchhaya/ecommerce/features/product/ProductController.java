package co.istad.chanchhaya.ecommerce.features.product;

import co.istad.chanchhaya.ecommerce.features.product.dto.CreateProductRequest;
import co.istad.chanchhaya.ecommerce.features.product.dto.PatchProductRequest;
import co.istad.chanchhaya.ecommerce.features.product.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PatchMapping("/{id}")
    public ProductResponse patchById(
            @PathVariable Integer id,
            @Valid @RequestBody PatchProductRequest patchProductRequest
    ) {
        return productService.patchById(id, patchProductRequest);
    }


    @GetMapping
    public Page<ProductResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return productService.findAll(pageNumber, pageSize);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNew(@Valid @RequestBody CreateProductRequest createProductRequest) {
        productService.createNew(createProductRequest);
    }

}
