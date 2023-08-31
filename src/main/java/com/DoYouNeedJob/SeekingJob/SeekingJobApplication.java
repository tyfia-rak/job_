package com.DoYouNeedJob.SeekingJob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.DoYouNeedJob.SeekingJob","controller"})
public class SeekingJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeekingJobApplication.class, args);
	}

}
