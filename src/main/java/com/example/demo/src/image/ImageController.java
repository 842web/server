package com.example.demo.src.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/images")
@RestController
@RequiredArgsConstructor
public class ImageController {
    @Autowired
    private final ImageService imageService;

    /**
     * 이미지 업로드 API
     * [POST] /images
     * @return BaseResponse<String>
     * */
//    @PostMapping("")
//    public BaseResponse<String> uploadPhoto(@RequestBody MultipartFile file) {
//        // Validation - 필수 입력 항목
//        if (file.isEmpty()) {
//            return new BaseResponse<>(PHOTOS_EMPTY_MANDATORY);
//        }
//
//        try {
//            String url = s3Service.upload(file, "image");
//            return new BaseResponse<>(url);
//        } catch (Exception exception) {
//            return new BaseResponse(UNKNOWN_ERROR);
//        }
//    }

    /**
     * 이미지 조회 API
     * [GET] /images/?type=
     * @return BaseResponse<List<Image>>
     * */

}
