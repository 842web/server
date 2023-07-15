package com.example.demo.src.test;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.image.ImageService;
import com.example.demo.src.image.model.PostImage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "테스트")
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final ImageService imageService;

    /**
     * JDBC 연결 테스트
     * */
    @Operation(summary = "JDBC 연결 테스트 API", description = "이런거")
    @GetMapping("/postImages")
    @ApiResponse(responseCode = "200", description = "성공")
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