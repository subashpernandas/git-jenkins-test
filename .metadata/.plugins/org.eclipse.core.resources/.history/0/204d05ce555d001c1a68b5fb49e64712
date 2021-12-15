package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repo.SubashRepo;


@RestController
@RequestMapping("/batch")
public class PersonController {

	@Autowired private JobLauncher jobLauncher;

	@Autowired private Job job;

	@Autowired private SubashRepo subashRepo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@GetMapping
	public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		LOGGER.info("Job status::::::From Subash Test:::"+ jobExecution.getStatus());
		LOGGER.info("Job is Running::::::From Subash Test:::::");
		while(jobExecution.isRunning()) {
			LOGGER.info("..........................");
		}
		return jobExecution.getStatus();
	}

	
	
	@PostMapping("/load")
    public BatchStatus Load(@RequestParam("filePath") String filePath) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        System.out.println("file path::::::::::::::::"+ filePath);
        LOGGER.info("Dynamic File Upload:::::::::::From Subash Test:::::::::;");
        JobExecution jobExecution = jobLauncher.run(job, new JobParametersBuilder()
                .addString("fileUploadName", filePath)
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());
        LOGGER.info("Dynamic File Upload::::::::::From Subash Test::::::::::;");
        return jobExecution.getStatus();

    }

	@GetMapping("/get")
	public List<Object[]> getSubashDetails(){
		List<Object[]> objectList = subashRepo.getSubashDetails();
		for(Object[] object : objectList) {
			System.out.println(object[0]);
			System.out.println(object[1]);
			System.out.println(object[2]);
		}
		return objectList;
	}
}
