package com.example.demo.src.test;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.image.ImageService;
import com.example.demo.src.image.model.PostImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final ImageService imageService;

    /**
     * JDBC 연결 테스트
     * */
    @GetMapping("/postImages")
    public BaseResponse<List<PostImage>> getPostImages() {
        // Validation

        try {
            List<PostImage> getPostImages = imageService.getPostImages();
            return new BaseResponse<>(getPostImages);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}