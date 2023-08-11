package com.example.demo.batch;


import com.example.demo.config.base.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobScheduler {

    private final JobLauncher jobLauncher;

    private final Job UserWithdraw_job1;

    @Scheduled(cron = "0  7 4 * * * ", zone = "Asia/Seoul")
    public void TaskletStart() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException{

        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);
        //parameter 에 대해 공부필요. 왜 파라미터에 값을 안 넣으니 실행이 안됐는지
         System.out.println("JobStart****");
        JobExecution execution = jobLauncher.run(UserWithdraw_job1, jobParameters);
        while (execution.isRunning()) {
            System.out.println("Job Execution: " + execution.getStatus());
         }
        System.out.println("Job Execution: " + execution.getStatus());

        }


}
