package com.edilson.justiniano.schedulermanagement.api.job.controller;

import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_CONCLUSION_DEADLINE;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_CONCLUSION_FROM;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.edilson.justiniano.schedulermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulermanagement.api.job.model.SchedulerResponse;
import com.edilson.justiniano.schedulermanagement.api.job.service.JobService;
import com.edilson.justiniano.schedulermanagement.base.request.JobRequestTestBuilder;
import com.edilson.justiniano.schedulermanagement.base.response.JobResponseTestBuilder;
import com.edilson.justiniano.schedulermanagement.base.response.SchedulerResponseTestBuilder;

/**
 * Unit tests for {@link JobController} class.
 */
@RunWith(MockitoJUnitRunner.class)
public class JobControllerTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobController jobController;

    @Test
    public void getJobsIsSuccessfullyRetrieved() {
        // given
        JobResponse jobResponse = JobResponseTestBuilder.aTwoHoursJob().build();
        List<JobResponse> expectedResponse = Collections.singletonList(jobResponse);

        // and
        given(jobService.searchJobs()).willReturn(expectedResponse);

        // when
        ResponseEntity<List<JobResponse>> actualResponse = jobController.getJobs();

        // then
        assertThat(actualResponse.getBody(), equalTo(expectedResponse));
    }

    @Test
    public void postJobIsSuccessfullyCreated() {
        // given
        JobRequest request = JobRequestTestBuilder.aTwoHoursJob().build();
        JobResponse expectedResponse = JobResponseTestBuilder.aTwoHoursJob().build();

        // and
        given(jobService.createJob(request)).willReturn(expectedResponse);

        // when
        ResponseEntity<JobResponse> actualResponse = jobController.postJob(request);

        // then
        assertThat(actualResponse.getBody(), equalTo(expectedResponse));
    }

    @Test
    public void postSchedulerIsSuccessfullyCreated() {
        // given
        SchedulerResponse expectedResponse = SchedulerResponseTestBuilder.aFullScheduler().build();
        given(jobService.createScheduler(DEFAULT_CONCLUSION_FROM, DEFAULT_CONCLUSION_DEADLINE)).willReturn(expectedResponse);

        // when
        ResponseEntity<SchedulerResponse> actualResponse = jobController
                .postScheduler(DEFAULT_CONCLUSION_FROM, DEFAULT_CONCLUSION_DEADLINE);

        // then
        assertThat(actualResponse.getBody(), equalTo(expectedResponse));
    }
}