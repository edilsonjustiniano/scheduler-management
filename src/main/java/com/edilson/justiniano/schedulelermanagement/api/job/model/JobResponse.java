package com.edilson.justiniano.schedulelermanagement.api.job.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JobResponse {

    private String id;
    private String description;
    private String conclusionDeadline;
    private float estimatedTime;
}
