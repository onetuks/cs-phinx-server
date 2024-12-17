package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.answer.AnswerEntity;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerEntityMongoRepository extends MongoRepository<AnswerEntity, String> {

  List<AnswerEntity> findAllByQuestionId(String questionId);
}
