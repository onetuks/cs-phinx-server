package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.ChoiceAnswerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChoiceAnswerEntityMongoRepository
    extends MongoRepository<ChoiceAnswerEntity, String> {}
