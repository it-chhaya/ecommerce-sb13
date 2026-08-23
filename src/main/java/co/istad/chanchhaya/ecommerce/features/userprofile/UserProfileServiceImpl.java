package co.istad.chanchhaya.ecommerce.features.userprofile;

import co.istad.chanchhaya.ecommerce.features.userprofile.dto.PatchUserProfileRequest;
import co.istad.chanchhaya.ecommerce.features.userprofile.dto.UserProfileResponse;
import co.istad.chanchhaya.ecommerce.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final Keycloak keycloak;
    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse patchUserProfile(PatchUserProfileRequest patchUserProfileRequest) {
        // TODO
        // Step 1: Patch userProfile in table
        String userId = AuthUtils.extractUserId();
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User profile has not been found"
                ));
        userProfileMapper.toEntity(patchUserProfileRequest, userProfile);
        userProfileRepository.save(userProfile);

        return userProfileMapper.toResponse(userProfile);
    }

}
