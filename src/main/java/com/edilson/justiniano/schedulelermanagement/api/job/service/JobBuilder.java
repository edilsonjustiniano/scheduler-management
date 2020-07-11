package com.edilson.justiniano.schedulelermanagement.api.job.service;

import static com.edilson.justiniano.schedulelermanagement.persistence.model.Job.DATETIME_FORMATTER;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.edilson.justiniano.schedulelermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulelermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulelermanagement.api.job.model.SchedulerResponse;
import com.edilson.justiniano.schedulelermanagement.persistence.model.Job;

@Component
public class JobBuilder {

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

    public SchedulerResponse buildSchedulerResponse(List<String> jobIds) {
        return SchedulerResponse.builder().jobIds(jobIds).build();
    }

    private LocalDateTime buildConclusionDeadline(JobRequest request) {
        return LocalDateTime.parse(request.getConclusionDeadline(), DATETIME_FORMATTER);
    }
}