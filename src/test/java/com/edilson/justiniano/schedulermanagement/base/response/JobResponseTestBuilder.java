package com.edilson.justiniano.schedulermanagement.base.response;

import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_CONCLUSION_DEADLINE;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_DESCRIPTION;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_NINE_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_SEVEN_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_SIX_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_TWO_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_NINE_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_SEVEN_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_SIX_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_TWO_HOURS;

import com.edilson.justiniano.schedulermanagement.api.job.model.JobResponse;

public final class JobResponseTestBuilder {

    private String id;
    private String description = DEFAULT_JOB_DESCRIPTION;
    private String conclusionDeadline = DEFAULT_CONCLUSION_DEADLINE;
    private float estimatedTime;

    private JobResponseTestBuilder() {
    }

    public static JobResponseTestBuilder aRequest() {
        return new JobResponseTestBuilder();
    }

    public JobResponseTestBuilder id(String id) {
        this.id = id;
        return this;
    }

    public JobResponseTestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobResponseTestBuilder conclusionDeadline(String conclusionDeadline) {
        this.conclusionDeadline = conclusionDeadline;
        return this;
    }

    public JobResponseTestBuilder estimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
        return this;
    }

    public static JobResponseTestBuilder aTwoHoursJob() {
        return aRequest().id(DEFAULT_JOB_ID_TWO_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_TWO_HOURS);
    }

    public static JobResponseTestBuilder aSixHoursJob() {
        return aRequest().id(DEFAULT_JOB_ID_SIX_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_SIX_HOURS);
    }

    public static JobResponseTestBuilder aSevenHoursJob() {
        return aRequest().id(DEFAULT_JOB_ID_SEVEN_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_SEVEN_HOURS);
    }

    public static JobResponseTestBuilder aNineHoursJob() {
        return aRequest().id(DEFAULT_JOB_ID_NINE_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_NINE_HOURS);
    }

    public JobResponse build() {
        return JobResponse.builder()
                .id(id)
                .description(description)
                .conclusionDeadline(conclusionDeadline)
                .estimatedTime(estimatedTime)
                .build();
    }
}
