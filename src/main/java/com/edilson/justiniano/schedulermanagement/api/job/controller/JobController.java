package com.edilson.justiniano.schedulermanagement.api.job.controller;

import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.JOB_URL_V1;
import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.REQUEST_PARAM_FROM;
import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.REQUEST_PARAM_TO;
import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.SCHEDULER_JOBS_URL_V1;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edilson.justiniano.schedulermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulermanagement.api.job.model.SchedulerResponse;
import com.edilson.justiniano.schedulermanagement.api.job.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JobController {

    private final JobService jobService;

    // I could use Pageable, but the idea is not create a complete CRUD application.
    @GetMapping(value = JOB_URL_V1)
    public ResponseEntity<List<JobResponse>> getJobs() {
        log.info("Getting the jobs.");

        return ResponseEntity
                .status(OK)
                .body(jobService.searchJobs());
    }

    @PostMapping(value = JOB_URL_V1, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<JobResponse> postJob(@RequestBody JobRequest request) {
        log.info("Posting a job.");

        // I could create a Spring Validator to validate the conclusion deadline, Check if it does have a valid format.
        // It means the format like: yyyy-MM-dd HH:mm:ss

        return ResponseEntity
                .status(CREATED)
                .body(jobService.createJob(request));
    }

    @PostMapping(value = SCHEDULER_JOBS_URL_V1)
    public ResponseEntity<SchedulerResponse> postScheduler(@RequestParam(REQUEST_PARAM_FROM) String from,
                                                           @RequestParam(REQUEST_PARAM_TO) String to) {
        log.info("Posting a scheduler.");

        return ResponseEntity
                .status(CREATED)
                .body(jobService.createScheduler(from, to));
    }
}
