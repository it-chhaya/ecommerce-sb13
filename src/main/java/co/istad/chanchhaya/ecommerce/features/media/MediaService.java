package co.istad.chanchhaya.ecommerce.features.media;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {


    /**
     * Update media draft status = true (bin)
     */
    void draftByName(String name);


    /**
     * Delete media by name
     */
    void deleteByName(String name);


    /**
     * Find medias by pageable
     */
    Page<MediaResponse> findAll(int pageNumber, int pageSize);


    /**
     * Find media by name
     */
    MediaResponse findByName(String name);


    /**
     * Upload media (multiple)
     */
    List<MediaResponse> upload(List<MultipartFile> files);


    /**
     * Upload media (single)
     */
    MediaResponse upload(MultipartFile file);
}
