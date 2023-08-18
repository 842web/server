package com.example.demo.web.controller;


import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.CryptographyUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Slf4j
@Tag(name = "테스트")
@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

   private final EntityManager em;

    @Transactional
    @GetMapping("/deleteuser")
    public String deltedfd(){
       userRepository.deleteAllByDay();
            return null;

    }

    @GetMapping("/decording")
    public String TestDecoring(String Link){

        try {
            CryptographyUtils cryptographyUtils = new CryptographyUtils();
            String userId = cryptographyUtils.decrypt(Link);
            System.out.println("decords userId: " + userId);
            return userId;
        }catch (Exception err){

            System.out.println(err);

            return null;

        }
    }
}