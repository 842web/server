package com.example.demo.web.controller;

import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.BaseResponse;
import com.example.demo.config.base.Code;
import com.example.demo.service.ImageService;
import com.example.demo.web.dto.request.ImageRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Tag(name = "이미지")
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
    @PostMapping("")
    public BaseResponse<String> uploadPhoto(@RequestBody MultipartFile file) throws IOException {
        String url = imageService.uploadImage(file, "image");
        return new BaseResponse<>(url);
    }

    /**
     * 이미지 조회 API
     * [GET] /images/?dirName=
     * @return BaseResponse<List<Image>>
     * */
    @GetMapping("")
    public BaseResponse<ArrayList<String>> getImages(@RequestParam String dirName) throws BaseException {
        ArrayList<String> imageList = imageService.getImages(dirName);
        return new BaseResponse<>(imageList);
    }

    /**
     * 이미지 삭제 API
     * [POST] /images
     * @return BaseResponse<String>
     * */
    @DeleteMapping("")
    public BaseResponse<String> deletePhoto(@RequestBody ImageRequestDto.DeleteImageDto request) throws BaseException {
        String[] imageList = request.getImageList();

        for (String image: imageList) {
            boolean isSuccess = imageService.deleteImage(image);

            if (!isSuccess) throw new BaseException(Code.IMAGE_DELETE_FAIL);
        }

        return new BaseResponse<>("이미지 삭제에 성공하였습니다. ");
    }

}
