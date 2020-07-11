package com.edilson.justiniano.schedulelermanagement.api.job.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.edilson.justiniano.schedulelermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulelermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulelermanagement.api.job.model.SchedulerResponse;
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

    public List<SchedulerResponse> createScheduler(String from, String to) {

        LocalDateTime fromDate = LocalDateTime.parse(from, Job.DATETIME_FORMATTER);
        LocalDateTime toDate = LocalDateTime.parse(to, Job.DATETIME_FORMATTER);

        List<Job> jobs = repository.findAllByConclusionDeadlineBetween(fromDate, toDate);

        List<SchedulerResponse> response = new ArrayList<>();
        List<String> jobIds = new ArrayList<>();
        AtomicReference<Float> estimatedTimeSum = new AtomicReference<>(0F);

        //TODO: Fix the logic.

        // Must handle a single job
        // Must handle multiple jobs, like one for 2.5 h the second one with 6h and the last one as 4 hours.
        jobs.forEach(job -> {
            if ((job.getEstimatedTime() + estimatedTimeSum.get()) <= 8) {
                estimatedTimeSum.updateAndGet(v -> (v + job.getEstimatedTime()));
                jobIds.add(job.getId());
            } else {
                response.add(builder.buildSchedulerResponse(new ArrayList<>(jobIds)));
                jobIds.clear();
                estimatedTimeSum.set(0F);
            }
        });

        return response;
    }

}
