package co.istad.chanchhaya.ecommerce.features.tag;

import co.istad.chanchhaya.ecommerce.features.tag.dto.TagRequest;
import co.istad.chanchhaya.ecommerce.features.tag.dto.TagResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagResponse createNew(@Valid @RequestBody TagRequest tagRequest) {
        return tagService.createNew(tagRequest);
    }

    @PutMapping("/{id}")
    public TagResponse updateById(@PathVariable Integer id, @Valid @RequestBody TagRequest tagRequest) {
        return tagService.updateById(id, tagRequest);
    }

    @GetMapping("/{id}")
    public TagResponse findById(@PathVariable Integer id) {
        return tagService.findById(id);
    }

    @GetMapping
    public Page<TagResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortById);
        return tagService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        tagService.deleteById(id);
    }

}
