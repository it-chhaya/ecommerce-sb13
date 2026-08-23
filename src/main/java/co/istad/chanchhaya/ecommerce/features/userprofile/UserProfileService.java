package co.istad.chanchhaya.ecommerce.features.userprofile;

import co.istad.chanchhaya.ecommerce.features.userprofile.dto.PatchUserProfileRequest;
import co.istad.chanchhaya.ecommerce.features.userprofile.dto.UserProfileResponse;

public interface UserProfileService {

    UserProfileResponse getUserProfile();

    UserProfileResponse patchUserProfile(PatchUserProfileRequest patchUserProfileRequest);

}
