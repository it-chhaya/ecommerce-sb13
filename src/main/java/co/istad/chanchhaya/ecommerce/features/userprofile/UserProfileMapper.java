package co.istad.chanchhaya.ecommerce.features.userprofile;

import co.istad.chanchhaya.ecommerce.features.userprofile.dto.PatchUserProfileRequest;
import co.istad.chanchhaya.ecommerce.features.userprofile.dto.UserProfileResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toEntity(PatchUserProfileRequest patchUserProfileRequest,
                  @MappingTarget UserProfile userProfile);

    UserProfileResponse toResponse(UserProfile userProfile,
                                   String firstName,
                                   String lastName);

}
