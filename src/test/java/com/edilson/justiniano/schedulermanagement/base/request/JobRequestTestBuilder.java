package com.edilson.justiniano.schedulermanagement.base.request;

import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_CONCLUSION_DEADLINE;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_DESCRIPTION;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_NINE_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_SEVEN_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_SIX_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_TWO_HOURS;

import com.edilson.justiniano.schedulermanagement.api.job.model.JobRequest;

public final class JobRequestTestBuilder {

    private String description = DEFAULT_JOB_DESCRIPTION;
    private String conclusionDeadline = DEFAULT_CONCLUSION_DEADLINE;
    private float estimatedTime;

    private JobRequestTestBuilder() {
    }

    public static JobRequestTestBuilder aRequest() {
        return new JobRequestTestBuilder();
    }

    public JobRequestTestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobRequestTestBuilder conclusionDeadline(String conclusionDeadline) {
        this.conclusionDeadline = conclusionDeadline;
        return this;
    }

    public JobRequestTestBuilder estimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
        return this;
    }

    public static JobRequestTestBuilder aTwoHoursJob() {
        return aRequest().estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_TWO_HOURS);
    }

    public static JobRequestTestBuilder aSixHoursJob() {
        return aRequest().estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_SIX_HOURS);
    }

    public static JobRequestTestBuilder aSevenHoursJob() {
        return aRequest().estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_SEVEN_HOURS);
    }

    public static JobRequestTestBuilder aNineHoursJob() {
        return aRequest().estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_NINE_HOURS);
    }

    public JobRequest build() {
        return JobRequest.builder()
                .description(description)
                .conclusionDeadline(conclusionDeadline)
                .estimatedTime(estimatedTime)
                .build();
    }
}
