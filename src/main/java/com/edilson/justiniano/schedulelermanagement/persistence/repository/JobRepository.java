package com.edilson.justiniano.schedulelermanagement.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edilson.justiniano.schedulelermanagement.persistence.model.Job;

public interface JobRepository extends MongoRepository<Job, String> {

}
