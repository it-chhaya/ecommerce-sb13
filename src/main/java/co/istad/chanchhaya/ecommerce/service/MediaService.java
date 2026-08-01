package co.istad.chanchhaya.ecommerce.service;

import co.istad.chanchhaya.ecommerce.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {

    /**
     * Upload media (multiple)
     */
    List<MediaResponse> upload(List<MultipartFile> files);


    /**
     * Upload media (single)
     */
    MediaResponse upload(MultipartFile file);
}
