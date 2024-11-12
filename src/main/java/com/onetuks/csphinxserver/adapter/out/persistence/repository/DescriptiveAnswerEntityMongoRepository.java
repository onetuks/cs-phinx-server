package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.DescriptiveAnswerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescriptiveAnswerEntityMongoRepository
    extends MongoRepository<DescriptiveAnswerEntity, String> {}
