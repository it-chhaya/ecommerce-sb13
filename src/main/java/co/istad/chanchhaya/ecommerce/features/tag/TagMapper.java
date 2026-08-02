package co.istad.chanchhaya.ecommerce.features.tag;

import co.istad.chanchhaya.ecommerce.features.tag.dto.TagRequest;
import co.istad.chanchhaya.ecommerce.features.tag.dto.TagResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag fromTagRequest(TagRequest tagRequest);

    void fromTagRequest(TagRequest tagRequest, @MappingTarget Tag tag);

    TagResponse toTagResponse(Tag tag);

}
