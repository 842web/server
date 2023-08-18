package com.example.demo.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

public class ImageRequestDto {

    @Getter @Setter
    public static class DeleteImageDto{
        @NotEmpty(message = "삭제할 이미지가 없습니다.")
        private String[] imageList;
    }

}
