package com.example.demo.batch;

import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;


@RequiredArgsConstructor
@Configuration
public class UserWithdrawBatch {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Bean(name = "UserWithdraw_job1")
    Job UserWithdraw_job1(){

        return jobBuilderFactory.get("UserWithdraw_job1")
                .start(UserWithdraw_job1_step1())
                .next(UserWithdraw_job1_step2())
                .build();
    }

    @Bean
    @JobScope
    Step UserWithdraw_job1_step1(){

        return stepBuilderFactory.get("UserWithdraw_job1_step1")
                .tasklet((stepContribution, chunkContext)->{

                    Long UserCount = userRepository.countAllByStatusAndUpdated_At();
                    System.out.println(UserCount);
                    if(UserCount == 0){


                    }
                        return RepeatStatus.FINISHED;

    }).build();

    }

    @Bean
    @JobScope
    Step UserWithdraw_job1_step2(){

        return stepBuilderFactory.get("UserWithdraw_job1_step2")
                .tasklet((stepContribution, chunkContext)->{

                    postRepository.findAll();
                    userRepository.deleteAllByDay();

                    return RepeatStatus.FINISHED;

                }).build();

    }
}
