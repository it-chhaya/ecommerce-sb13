package co.istad.chanchhaya.ecommerce.features.tag;

import co.istad.chanchhaya.ecommerce.features.tag.dto.TagRequest;
import co.istad.chanchhaya.ecommerce.features.tag.dto.TagResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public TagResponse createNew(TagRequest tagRequest) {
        if (tagRepository.existsByName(tagRequest.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag name already exists");
        }
        Tag tag = tagMapper.fromTagRequest(tagRequest);
        tag.setIsDeleted(false);
        tag = tagRepository.save(tag);
        return tagMapper.toTagResponse(tag);
    }

    @Override
    public TagResponse updateById(Integer id, TagRequest tagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag has not been found"));

        if (!tag.getName().equals(tagRequest.name()) && tagRepository.existsByName(tagRequest.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tag name already exists");
        }

        tagMapper.fromTagRequest(tagRequest, tag);
        tag = tagRepository.save(tag);
        return tagMapper.toTagResponse(tag);
    }

    @Override
    public TagResponse findById(Integer id) {
        return tagRepository.findById(id)
                .map(tagMapper::toTagResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag has not been found"));
    }

    @Override
    public Page<TagResponse> findAll(Pageable pageable) {
        return tagRepository.findAll(pageable)
                .map(tagMapper::toTagResponse);
    }

    @Override
    public void deleteById(Integer id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag has not been found"));
        tagRepository.delete(tag);
    }
}
