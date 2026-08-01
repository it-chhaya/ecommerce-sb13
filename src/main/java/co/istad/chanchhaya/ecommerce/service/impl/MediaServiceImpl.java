package co.istad.chanchhaya.ecommerce.service.impl;

import co.istad.chanchhaya.ecommerce.dto.MediaResponse;
import co.istad.chanchhaya.ecommerce.entity.Media;
import co.istad.chanchhaya.ecommerce.repository.MediaRepository;
import co.istad.chanchhaya.ecommerce.service.MediaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Value("${media.location}")
    private String mediaLocation;

    @Value("${media.client-path}")
    private String mediaClientPath;

    @Value("${media.base-uri}")
    private String mediaBaseUri;

    private final static String MB = "MB";

    @Override
    public MediaResponse upload(MultipartFile file) {
        // TODO
        // 1. Create path object (ផ្ទុកទីតាំង file)
        String name = UUID.randomUUID().toString();
        // e.g. Vital.png
        int lastIndexDot = file.getOriginalFilename().lastIndexOf('.');
        String extension = file.getOriginalFilename().substring(lastIndexDot + 1);
        Path path = Paths.get(mediaLocation + name + "." + extension);
        log.info("Uploading media location: {}", path);

        // 2. Copy file
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Media has been uploaded failed"
            );
        }

        // 3. Save into database table
        Media media = new Media();
        media.setName(name);
        media.setExtension(extension);
        media.setSize((float) file.getSize());
        media.setMediaType(file.getContentType());
        media.setIsDraft(false);
        media = mediaRepository.save(media);

        return buildMediaResponse(media);
    }

    private MediaResponse buildMediaResponse(Media media) {
        // 1MB = 1_000_000B
        return MediaResponse.builder()
                .id(media.getId())
                .name(media.getName())
                .extension(media.getExtension())
                .mediaType(media.getMediaType())
                .size(media.getSize() / 1_000_000)
                .measurement(MB)
                .uri(buildMediaUri(media)) // http://localhost:1333/media/78689a24-551c-4575-9831-a4ec8e2bb0ef.png
                .build();
    }

    private String buildMediaUri(Media media) {
        return mediaBaseUri +
                mediaClientPath +
                "/" + media.getName() +
                "." + media.getExtension();
    }

}
