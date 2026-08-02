package co.istad.chanchhaya.ecommerce.features.tag;

import co.istad.chanchhaya.ecommerce.features.tag.dto.TagRequest;
import co.istad.chanchhaya.ecommerce.features.tag.dto.TagResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    TagResponse createNew(TagRequest tagRequest);

    TagResponse updateById(Integer id, TagRequest tagRequest);

    TagResponse findById(Integer id);

    Page<TagResponse> findAll(Pageable pageable);

    void deleteById(Integer id);

}
