package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.AnswerEntity;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerEntityMongoRepository extends MongoRepository<AnswerEntity, String> {

  Optional<AnswerEntity> findByQuestionId(String questionId);
}
