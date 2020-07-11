package com.edilson.justiniano.schedulelermanagement.api.job.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JobRequest {

    private String description;
    private String conclusionDeadline;
    private float estimatedTime;
}
