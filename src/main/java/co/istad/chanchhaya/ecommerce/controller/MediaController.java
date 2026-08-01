package co.istad.chanchhaya.ecommerce.controller;

import co.istad.chanchhaya.ecommerce.dto.MediaResponse;
import co.istad.chanchhaya.ecommerce.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/multiple")
    public List<MediaResponse> upload(@RequestPart List<MultipartFile> files) {
        return mediaService.upload(files);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MediaResponse upload(@RequestPart MultipartFile file) {
        return mediaService.upload(file);
    }

}
