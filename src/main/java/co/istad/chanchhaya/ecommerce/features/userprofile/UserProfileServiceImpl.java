package co.istad.chanchhaya.ecommerce.features.userprofile;

import co.istad.chanchhaya.ecommerce.features.userprofile.dto.PatchUserProfileRequest;
import co.istad.chanchhaya.ecommerce.features.userprofile.dto.UserProfileResponse;
import co.istad.chanchhaya.ecommerce.security.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${keycloak.realm}")
    private String realm;

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

        // Step 2: Update userProfile in keycloak
        UsersResource usersResource = keycloak.realm(realm).users();
        UserResource userResource = usersResource.get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        if (patchUserProfileRequest.firstName() != null)
            userRepresentation.setFirstName(patchUserProfileRequest.firstName());
        if (patchUserProfileRequest.lastName() != null)
            userRepresentation.setLastName(patchUserProfileRequest.lastName());

        userResource.update(userRepresentation);

        return userProfileMapper.toResponse(userProfile,
                patchUserProfileRequest.firstName(),
                patchUserProfileRequest.lastName());
    }

}
