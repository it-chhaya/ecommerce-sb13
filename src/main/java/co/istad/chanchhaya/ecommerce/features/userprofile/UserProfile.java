package co.istad.chanchhaya.ecommerce.features.userprofile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_profiles")
public class UserProfile {
    @Id
    private String id; // Keycloak User ID
    @Column(length = 6)
    private String gender;
    private String biography;
    private String facebookProfile;
    private String telegramProfile;
    private String pictureProfile;
}
