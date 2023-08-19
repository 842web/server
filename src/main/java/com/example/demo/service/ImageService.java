package com.example.demo.service;

import com.example.demo.config.base.BaseException;
import com.example.demo.domain.mapping.PostImage;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;

public interface ImageService {
    /**
     * 파일 업로드
     * @Input MultipartFile 파일, String 폴더명
     * @Output String 파일 url
     * Multipart 파일을 File로 전환한 후 업로드하는 함수
     * */
    String uploadImage (MultipartFile multipartFile, String dirName) throws IOException;

    /**
     * 파일 삭제
     * @Input String 파일 url
     * 등록된 파일을 삭제하는 함수
     * */
    boolean deleteImage (String url) throws BaseException;

    /**
     * 파일 조회
     * @Input String 디렉토리 이름
     * 해당 디렉토리에 존재하는 모든 파일을 읽어오는 함수
     * */
    ArrayList<String> getImages (String dirName) throws BaseException;

    /**
     * 포스트 이미지 조회
     * 포스트 이미지 목록을 조회하는 함수
     * */
    ArrayList<PostImage> getPostImages() throws BaseException;
}
