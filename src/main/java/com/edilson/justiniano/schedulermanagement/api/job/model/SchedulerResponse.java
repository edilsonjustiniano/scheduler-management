package com.edilson.justiniano.schedulermanagement.api.job.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SchedulerResponse {

    List<List<String>> jobIds;
}
