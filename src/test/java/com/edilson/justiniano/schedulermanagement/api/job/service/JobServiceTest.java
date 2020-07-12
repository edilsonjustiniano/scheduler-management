package com.edilson.justiniano.schedulermanagement.api.job.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.edilson.justiniano.schedulermanagement.api.job.model.JobRequest;
import com.edilson.justiniano.schedulermanagement.api.job.model.JobResponse;
import com.edilson.justiniano.schedulermanagement.base.entity.JobTestBuilder;
import com.edilson.justiniano.schedulermanagement.base.request.JobRequestTestBuilder;
import com.edilson.justiniano.schedulermanagement.base.response.JobResponseTestBuilder;
import com.edilson.justiniano.schedulermanagement.persistence.model.Job;
import com.edilson.justiniano.schedulermanagement.persistence.repository.JobRepository;

/**
 * Unit tests for {@link JobService} class.
 */
@RunWith(MockitoJUnitRunner.class)
public class JobServiceTest {

    @Mock
    private JobRepository repository;

    @Mock
    private JobBuilder builder;

    @InjectMocks
    private JobService jobService;

    @Test
    public void searchJobsReturnsAllTheJobs() {
        // given
        Job jobTwoHours = JobTestBuilder.aTwoHoursJob().build();
        Job jobSixHours = JobTestBuilder.aSixHoursJob().build();
        Job jobSevenHours = JobTestBuilder.aSevenHoursJob().build();
        Job jobNineHours = JobTestBuilder.aNineHoursJob().build();
        List<Job> jobs = Arrays.asList(jobTwoHours, jobSixHours, jobSevenHours, jobNineHours);

        // and
        JobResponse responseTwoHours = JobResponseTestBuilder.aTwoHoursJob().build();
        JobResponse responseSixHours = JobResponseTestBuilder.aSixHoursJob().build();
        JobResponse responseSevenHours = JobResponseTestBuilder.aSevenHoursJob().build();
        JobResponse responseNineHours = JobResponseTestBuilder.aNineHoursJob().build();

        // and
        given(repository.findAll()).willReturn(jobs);

        // and
        List<JobResponse> expectedResponse = Arrays.asList(responseTwoHours, responseSixHours, responseSevenHours, responseNineHours);
        given(builder.buildJobResponse(jobTwoHours)).willReturn(responseTwoHours);
        given(builder.buildJobResponse(jobSixHours)).willReturn(responseSixHours);
        given(builder.buildJobResponse(jobSevenHours)).willReturn(responseSevenHours);
        given(builder.buildJobResponse(jobNineHours)).willReturn(responseNineHours);

        // when
        List<JobResponse> actualResponse = jobService.searchJobs();

        // then
        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    public void createJobShoudReturnTheCreatedJob() {
        // given
        JobRequest request = JobRequestTestBuilder.aTwoHoursJob().build();
        Job job = JobTestBuilder.aTwoHoursJob().build();
        JobResponse expectedResponse = JobResponseTestBuilder.aTwoHoursJob().build();

        // and
        given(builder.buildJob(request)).willReturn(job);
        given(repository.save(job)).willReturn(job);
        given(builder.buildJobResponse(job)).willReturn(expectedResponse);

        // when
        JobResponse actualResponse = jobService.createJob(request);

        // then
        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    public void createAFullSchedulerShouldReturnIt() {
        // given
        

        // when

        // then
    }
}