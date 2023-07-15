package com.example.demo.src.image;

import com.example.demo.src.image.model.PostImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ImageDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // R(Read)
    public List<PostImage> getPostImages(){
    String getPostImagesQuery = "SELECT * FROM PostImage";
        return this.jdbcTemplate.query(getPostImagesQuery,
                (rs,rowNum) -> new PostImage(
                        rs.getLong("postImageIdx"),
                        rs.getString("url")
                ));
    }
}
