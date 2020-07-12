package com.edilson.justiniano.schedulermanagement.base.entity;

import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_CONCLUSION_DEADLINE_DATETIME;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_DESCRIPTION;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_NINE_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_SEVEN_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_SIX_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ESTIMATED_TIME_TWO_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_NINE_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_SEVEN_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_SIX_HOURS;
import static com.edilson.justiniano.schedulermanagement.base.DefaultConstants.DEFAULT_JOB_ID_TWO_HOURS;

import java.time.LocalDateTime;

import com.edilson.justiniano.schedulermanagement.persistence.model.Job;

public final class JobTestBuilder {

    private String id;
    private String description = DEFAULT_JOB_DESCRIPTION;
    private LocalDateTime conclusionDeadline = DEFAULT_CONCLUSION_DEADLINE_DATETIME;
    private float estimatedTime;

    private JobTestBuilder() {
    }

    public static JobTestBuilder aJob() {
        return new JobTestBuilder();
    }

    public JobTestBuilder id(String id) {
        this.id = id;
        return this;
    }

    public JobTestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public JobTestBuilder conclusionDeadline(LocalDateTime conclusionDeadline) {
        this.conclusionDeadline = conclusionDeadline;
        return this;
    }

    public JobTestBuilder estimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
        return this;
    }

    public static JobTestBuilder aTwoHoursJob() {
        return aJob().id(DEFAULT_JOB_ID_TWO_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_TWO_HOURS);
    }

    public static JobTestBuilder aSixHoursJob() {
        return aJob().id(DEFAULT_JOB_ID_SIX_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_SIX_HOURS);
    }

    public static JobTestBuilder aSevenHoursJob() {
        return aJob().id(DEFAULT_JOB_ID_SEVEN_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_SEVEN_HOURS);
    }

    public static JobTestBuilder aNineHoursJob() {
        return aJob().id(DEFAULT_JOB_ID_NINE_HOURS).estimatedTime(DEFAULT_JOB_ESTIMATED_TIME_NINE_HOURS);
    }

    public Job build() {
        return Job.builder()
                .id(id)
                .description(description)
                .conclusionDeadline(conclusionDeadline)
                .estimatedTime(estimatedTime)
                .build();
    }
}
