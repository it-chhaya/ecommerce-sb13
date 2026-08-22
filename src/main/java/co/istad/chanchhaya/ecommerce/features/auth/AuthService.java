package co.istad.chanchhaya.ecommerce.features.auth;

import co.istad.chanchhaya.ecommerce.features.auth.dto.RegisterRequest;

public interface AuthService {

    // ចុះឈ្មោះ user ចូលទៅក្នុង keycloak
    void register(RegisterRequest registerRequest);

}
