package co.istad.chanchhaya.ecommerce.features.userprofile.dto;

public record UserProfileResponse(
        String id,
        String firstName,
        String lastName,
        String gender,
        String biography,
        String facebookProfile,
        String telegramProfile,
        String pictureProfile
) {
}
