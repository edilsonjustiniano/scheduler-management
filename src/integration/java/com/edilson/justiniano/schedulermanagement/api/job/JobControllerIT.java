package com.edilson.justiniano.schedulermanagement.api.job;

import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.JOB_URL_V1;
import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.REQUEST_PARAM_FROM;
import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.REQUEST_PARAM_TO;
import static com.edilson.justiniano.schedulermanagement.api.job.ApiConstants.SCHEDULER_JOBS_URL_V1;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.edilson.justiniano.schedulermanagement.persistence.model.Job;
import com.edilson.justiniano.schedulermanagement.persistence.repository.JobRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class JobControllerIT {

    private static final String DEFAULT_ID = "1";
    private static final String DEFAULT_DESCRIPTION = "description";
    public static final String DEFAULT_CONCLUSION_FROM = "2020-07-01 23:59:59";
    public static final String DEFAULT_CONCLUSION_DEADLINE = "2020-07-20 23:59:59";
    public static final LocalDateTime DEFAULT_CONCLUSION_DEADLINE_DATETIME = LocalDateTime.of(2020, 7, 20, 23, 59, 59);
    private static final float DEFAULT_ESTIMATED_TIME = 2F;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Job job;

    @MockBean
    private JobRepository repository;

    @Test
    public void getJobsShouldReturnAllOfThem() throws Exception {
        // given
        given(job.getId()).willReturn(DEFAULT_ID);
        given(job.getDescription()).willReturn(DEFAULT_DESCRIPTION);
        given(job.getConclusionDeadline()).willReturn(DEFAULT_CONCLUSION_DEADLINE_DATETIME);
        given(job.getEstimatedTime()).willReturn(DEFAULT_ESTIMATED_TIME);

        given(repository.findAll()).willReturn(Collections.singletonList(job));

        // when
        mockMvc.perform(get(JOB_URL_V1))
                .andExpect(status().isOk());

        // then
        verify(repository).findAll();
    }

    @Test
    public void createSchedulerShouldReturnIt() throws Exception {
        // given
        given(job.getId()).willReturn(DEFAULT_ID);
        given(job.getDescription()).willReturn(DEFAULT_DESCRIPTION);
        given(job.getConclusionDeadline()).willReturn(DEFAULT_CONCLUSION_DEADLINE_DATETIME);
        given(job.getEstimatedTime()).willReturn(DEFAULT_ESTIMATED_TIME);

        given(repository.findAllByConclusionDeadlineBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(Collections.singletonList(job));

        // when
        mockMvc.perform(post(SCHEDULER_JOBS_URL_V1)
                .queryParam(REQUEST_PARAM_FROM, DEFAULT_CONCLUSION_FROM)
                .queryParam(REQUEST_PARAM_TO, DEFAULT_CONCLUSION_DEADLINE))
                .andExpect(status().isCreated());

        // then
        verify(repository).findAllByConclusionDeadlineBetween(any(LocalDateTime.class), any(LocalDateTime.class));
    }
}
