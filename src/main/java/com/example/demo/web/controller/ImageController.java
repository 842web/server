package com.example.demo.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "이미지")
@RequestMapping("/images")
@RestController
@RequiredArgsConstructor
public class ImageController {

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
