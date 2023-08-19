package com.example.demo.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.demo.config.base.BaseException;
import com.example.demo.config.base.Code;
import com.example.demo.domain.mapping.PostImage;
import com.example.demo.repository.PostImageRepository;
import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final AmazonS3 amazonS3Client;

    @Autowired
    PostImageRepository postImageRepository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String uploadImage(MultipartFile file, String dirName) throws IOException {
        String fileName = dirName + "/" + new Date().getTime() + "_" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        PutObjectRequest object = new PutObjectRequest(bucket, fileName, file.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3Client.putObject(object);
        log.info("### S3Service: " + fileName + " 업로드에 성공하였습니다.");
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    @Override
    public boolean deleteImage(String url) throws BaseException {
        String absPath = url.replace("https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/", "");
        String name = URLDecoder.decode(absPath, StandardCharsets.UTF_8);

        try {
            amazonS3Client.deleteObject(bucket, name);
            log.info("### S3Service: " + name + " 파일이 삭제되었습니다.");

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new BaseException(Code.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ArrayList<String> getImages(String dirName) {
        ArrayList<String> list = new ArrayList<>();

        ListObjectsRequest listObject = new ListObjectsRequest();
        listObject.setBucketName(bucket);
        listObject.setPrefix(dirName);

        ObjectListing objects = amazonS3Client.listObjects(listObject);
        for (S3ObjectSummary objectSummary: objects.getObjectSummaries()) {
            String url = "https://" + bucket + ".s3.ap-northeast-2.amazonaws.com/" + objectSummary.getKey();
            list.add(url);
        }
        return list;
    }

    @Override
    public ArrayList<PostImage> getPostImages() {
        return (ArrayList<PostImage>) postImageRepository.findAll();
    }

}
