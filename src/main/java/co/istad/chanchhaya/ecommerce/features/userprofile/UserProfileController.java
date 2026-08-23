package co.istad.chanchhaya.ecommerce.features.userprofile;

import co.istad.chanchhaya.ecommerce.features.userprofile.dto.PatchUserProfileRequest;
import co.istad.chanchhaya.ecommerce.features.userprofile.dto.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PatchMapping
    public UserProfileResponse patchUserProfile(
            @Valid
            @RequestBody
            PatchUserProfileRequest patchUserProfileRequest
    ) {
        return userProfileService.patchUserProfile(patchUserProfileRequest);
    }

}
