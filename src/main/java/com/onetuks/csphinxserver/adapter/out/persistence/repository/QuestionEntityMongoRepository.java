package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.question.QuestionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionEntityMongoRepository extends MongoRepository<QuestionEntity, String> {}
