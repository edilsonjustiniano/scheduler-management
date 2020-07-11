package com.edilson.justiniano.schedulelermanagement.api.job.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.edilson.justiniano.schedulelermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulelermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulelermanagement.persistence.model.Job;
import com.edilson.justiniano.schedulelermanagement.persistence.repository.JobRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class JobService {

    private final JobRepository repository;
    private final JobBuilder builder;

    public List<JobResponse> searchJobs() {
        log.info("Searching the jobs.");

        return repository.findAll().stream()
                .map(builder::buildJobResponse)
                .collect(Collectors.toList());
    }

    public JobResponse createJob(JobRequest request) {
        log.info("Creating a new job.");

        Job job = builder.buildJob(request);
        repository.save(job);

        return builder.buildJobResponse(job);
    }
}
