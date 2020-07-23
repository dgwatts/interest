package com.github.dgwatts.interest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepo extends MongoRepository<InterestDetails, Long> {



}
