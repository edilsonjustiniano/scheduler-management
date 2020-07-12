package com.edilson.justiniano.schedulermanagement.api.job.service;

import static com.edilson.justiniano.schedulermanagement.persistence.model.Job.DATETIME_FORMATTER;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.edilson.justiniano.schedulermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulermanagement.api.job.model.SchedulerResponse;
import com.edilson.justiniano.schedulermanagement.persistence.model.Job;

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

    public SchedulerResponse buildSchedulerResponse(List<List<String>> jobMatrix) {
        return SchedulerResponse.builder()
                .jobIds(jobMatrix)
                .build();
    }

    private LocalDateTime buildConclusionDeadline(JobRequest request) {
        return LocalDateTime.parse(request.getConclusionDeadline(), DATETIME_FORMATTER);
    }
}
