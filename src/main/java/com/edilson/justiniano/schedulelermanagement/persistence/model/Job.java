package com.edilson.justiniano.schedulelermanagement.persistence.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Job {

    @Id
    private String id;
    private String description;
    private LocalDateTime conclusionDeadline;
    private float estimatedTime; // Estimated time in hours 0.5, 1, 1.5...
}
