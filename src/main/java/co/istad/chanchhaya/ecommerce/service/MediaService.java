package co.istad.chanchhaya.ecommerce.service;

import co.istad.chanchhaya.ecommerce.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    /**
     * Upload media (single)
     */
    MediaResponse upload(MultipartFile file);
}
