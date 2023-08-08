package com.example.demo.batch;

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

import javax.persistence.EntityManagerFactory;

@RequiredArgsConstructor
@Configuration
public class Myjob {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final EntityManagerFactory entityManagerFactory;

    private final UserRepository userRepository;

    @Bean(name = "myJob_job1")
    Job MyJob_job1(){

        return jobBuilderFactory.get("myJob_job1")
                .start(myJob_job1_step1())
                .next(myJob_job1_step2())
                .build();
    }

    @Bean
    @JobScope
    Step myJob_job1_step1(){

        return stepBuilderFactory.get("myJob_job1_step1")
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
    Step myJob_job1_step2(){

        return stepBuilderFactory.get("myJob_job1_step2")
                .tasklet((stepContribution, chunkContext)->{

                    userRepository.deleteAllByDay();

                    return RepeatStatus.FINISHED;

                }).build();

    }
}
