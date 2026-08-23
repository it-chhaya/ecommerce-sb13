package co.istad.chanchhaya.ecommerce.features.userprofile.dto;

public record PatchUserProfileRequest(
        String firstName,
        String lastName,
        String gender,
        String biography,
        String facebookProfile,
        String telegramProfile,
        String pictureProfile
) {
}
