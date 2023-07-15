package com.example.demo.src.image;

import com.example.demo.config.BaseException;
import com.example.demo.src.image.model.PostImage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Slf4j
@Service
@AllArgsConstructor
public class ImageService {
    private final ImageDao imageDao;

    // R(Read)
    public List<PostImage> getPostImages() throws BaseException {
        try{
            List<PostImage> getPostImages = imageDao.getPostImages();
            return getPostImages;
        }
        catch (Exception exception) {
            log.error("Error!", exception);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
