package com.edilson.justiniano.schedulermanagement.base.response;

import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_SEVEN_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_SIX_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_TWO_HOURS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.edilson.justiniano.schedulermanagement.api.job.model.SchedulerResponse;

public final class SchedulerResponseTestBuilder {

    List<List<String>> jobIds = new ArrayList<>();

    private SchedulerResponseTestBuilder() {
    }

    public static SchedulerResponseTestBuilder aScheduler() {
        return new SchedulerResponseTestBuilder();
    }

    public SchedulerResponseTestBuilder jobIds(List<List<String>> jobIds) {
        this.jobIds = jobIds;
        return this;
    }

    public static SchedulerResponseTestBuilder aFullScheduler() {
        return aScheduler().jobIds(Arrays.asList(Arrays.asList(DEFAULT_JOB_ID_TWO_HOURS, DEFAULT_JOB_ID_SIX_HOURS),
                Collections.singletonList(DEFAULT_JOB_ID_SEVEN_HOURS)));
    }

    public static SchedulerResponseTestBuilder aMinimalScheduler() {
        return aScheduler().jobIds(Collections.singletonList(Collections.singletonList(DEFAULT_JOB_ID_TWO_HOURS)));
    }

    public SchedulerResponse build() {
        return SchedulerResponse.builder()
                .jobIds(jobIds)
                .build();
    }
}
