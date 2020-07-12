package com.edilson.justiniano.schedulermanagement.api.job.service;

import static com.edilson.justiniano.schedulermanagement.persistence.model.Job.JOB_LIMIT_TIME;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.edilson.justiniano.schedulermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulermanagement.api.job.model.SchedulerResponse;
import com.edilson.justiniano.schedulermanagement.persistence.model.Job;
import com.edilson.justiniano.schedulermanagement.persistence.repository.JobRepository;

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

    public SchedulerResponse createScheduler(String from, String to) {
        log.info("Creating the scheduler of jobs. From: {} and To: {}.", from, to);
        LocalDateTime fromDate = LocalDateTime.parse(from, Job.DATETIME_FORMATTER);
        LocalDateTime toDate = LocalDateTime.parse(to, Job.DATETIME_FORMATTER);

        List<Job> jobs = repository.findAllByConclusionDeadlineBetween(fromDate, toDate);

        List<String> jobsInEightHours = new ArrayList<>();
        List<List<String>> jobMatrix = new ArrayList<>();
        AtomicReference<Float> estimatedTimeSum = new AtomicReference<>(0F); // using Atomic float in order to change it inside the stream

        jobs.stream()
                .filter(Job::isEstimatedTimeUnderLimit)
                .forEach(job -> {
                    if ((job.getEstimatedTime() + estimatedTimeSum.get()) <= JOB_LIMIT_TIME) {
                        estimatedTimeSum.updateAndGet(v -> (v + job.getEstimatedTime())); // Increment the sum of estimatedTime
                    } else {
                        jobMatrix.add(new ArrayList<>(jobsInEightHours)); //Clone/Copy the JOB IDs line to the matrix of JOB IDs
                        jobsInEightHours.clear(); // Clean the JOB Ids list, once the estimated time reached out
                        estimatedTimeSum.set(job.getEstimatedTime()); // Reset the sum of estimatedTime to the current job estimated time
                    }
                    // Add the current JOB Id to the list of jobs.
                    jobsInEightHours.add(job.getId());
                });

        // At the end, add the current list of JOB IDs (the last small than 8 hours) to the matrix of jobIds.
        jobMatrix.add(jobsInEightHours);

        return builder.buildSchedulerResponse(jobMatrix);
    }
}
