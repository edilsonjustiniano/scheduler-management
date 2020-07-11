package com.edilson.justiniano.schedulelermanagement.persistence.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edilson.justiniano.schedulelermanagement.persistence.model.Job;

public interface JobRepository extends MongoRepository<Job, String> {

    List<Job> findAllByConclusionDeadlineBetween(LocalDateTime from, LocalDateTime to);
}
