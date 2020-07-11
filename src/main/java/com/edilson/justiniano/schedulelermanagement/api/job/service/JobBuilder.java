package com.edilson.justiniano.schedulelermanagement.api.job.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.edilson.justiniano.schedulelermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulelermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulelermanagement.persistence.model.Job;

@Component
public class JobBuilder {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Job buildJob(JobRequest request) {
        return Job.builder()
                .description(request.getDescription())
                .estimatedTime(request.getEstimatedTime())
                .conclusionDeadline(buildConclusionDeadline(request))
                .build();
    }

    public JobResponse buildJobResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .description(job.getDescription())
                .conclusionDeadline(job.getConclusionDeadline().toString())
                .estimatedTime(job.getEstimatedTime())
                .build();
    }

    private LocalDateTime buildConclusionDeadline(JobRequest request) {
        return LocalDateTime.parse(request.getConclusionDeadline(), DATETIME_FORMATTER);
    }
}
