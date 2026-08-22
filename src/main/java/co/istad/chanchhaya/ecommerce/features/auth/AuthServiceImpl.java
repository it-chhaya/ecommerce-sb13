package co.istad.chanchhaya.ecommerce.features.auth;

import co.istad.chanchhaya.ecommerce.features.auth.dto.RegisterRequest;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    @Override
    public void register(RegisterRequest registerRequest) {
        // Create UserRepresentation
        UserRepresentation user = new UserRepresentation();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        user.setFirstName(registerRequest.firstName());
        user.setLastName(registerRequest.lastName());

        // Validate password and confirmed password
        if (!registerRequest.password().equals(registerRequest.confirmedPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Passwords do not match. Please try again");
        }

        // Prepare password credential
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(registerRequest.password());
        user.setCredentials(List.of(credential));

        // Default value
        user.setEmailVerified(true);
        user.setEnabled(true);

        // Save into keycloak
        UsersResource usersResource = keycloak.realm(realm)
                .users();

        try (Response response = usersResource.create(user)) {
            // TODO: failed (409, 401, 403, ...), succeed (201, 200, ...)
            log.info("Response status code: {}", response.getStatus());
            if (response.getStatus() == HttpStatus.CREATED.value()) {
                log.info("User {} created successfully", user.getUsername());
            }
        }
    }

}
