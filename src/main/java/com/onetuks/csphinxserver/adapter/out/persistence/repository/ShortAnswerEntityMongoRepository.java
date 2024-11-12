package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.ShortAnswerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortAnswerEntityMongoRepository
    extends MongoRepository<ShortAnswerEntity, String> {}
