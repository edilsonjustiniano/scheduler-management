package com.edilson.justiniano.schedulelermanagement.api.job.controller;

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

import com.edilson.justiniano.schedulelermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulelermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulelermanagement.api.job.model.SchedulerResponse;
import com.edilson.justiniano.schedulelermanagement.api.job.service.JobService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JobController {

    private static final String JOBS_URL = "/schedulermanagement/v1/jobs";
    private static final String REQUEST_PARAM_FROM = "from";
    private static final String REQUEST_PARAM_TO = "to";

    private final JobService jobService;

    // I could use Pageable, but the idea is not create a complete CRUD application.
    @GetMapping(value = JOBS_URL)
    public ResponseEntity<List<JobResponse>> getJobs() {
        log.info("Getting the jobs.");

        return ResponseEntity
                .status(OK)
                .body(jobService.searchJobs());
    }

    @PostMapping(value = JOBS_URL, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<JobResponse> postJob(@RequestBody JobRequest request) {
        log.info("Posting a job.");

        // TODO: Create a validator, just to validate the data format parsing

        return ResponseEntity
                .status(CREATED)
                .body(jobService.createJob(request));
    }

    @PostMapping(value = JOBS_URL + "/scheduler")
    public ResponseEntity<List<SchedulerResponse>> postScheduler(@RequestParam(REQUEST_PARAM_FROM) String from,
                                                                 @RequestParam(REQUEST_PARAM_TO) String to) {
        log.info("Posting a scheduler.");

        return ResponseEntity
                .status(CREATED)
                .body(jobService.createScheduler(from, to));
    }
}
