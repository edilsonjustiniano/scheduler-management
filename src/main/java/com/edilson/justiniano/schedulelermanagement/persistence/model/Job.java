package com.edilson.justiniano.schedulelermanagement.persistence.model;

import static com.edilson.justiniano.schedulelermanagement.persistence.PersistenceConstants.LOG_CONCLUSION_DEADLINE;
import static com.edilson.justiniano.schedulelermanagement.persistence.PersistenceConstants.LOG_ESTIMATED_TIME;
import static com.edilson.justiniano.schedulelermanagement.persistence.PersistenceConstants.LOG_ID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Id
    private String id;
    private String description;
    private LocalDateTime conclusionDeadline;
    private float estimatedTime; // Estimated time in hours 0.5, 1, 1.5...

    @Transient
    public String log() {
        return new StringBuilder(LOG_ID)
                .append(this.id)
                .append(LOG_CONCLUSION_DEADLINE)
                .append(this.conclusionDeadline)
                .append(LOG_ESTIMATED_TIME)
                .append(this.estimatedTime)
                .toString();
    }
}
