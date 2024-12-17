package com.onetuks.csphinxserver.adapter.out.persistence.converter;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.question.CollectionEntity;
import com.onetuks.csphinxserver.domain.question.Collection;
import org.springframework.stereotype.Component;

@Component
public class CollectionConverter {

  public CollectionEntity toEntity(Collection domain) {
    return new CollectionEntity(
        domain.collectionId(),
        domain.collectionName(),
        domain.collectionType(),
        domain.includedQuestionIds());
  }

  public Collection toDomain(CollectionEntity entity) {
    return new Collection(
        entity.getId(),
        entity.getCollectionName(),
        entity.getCollectionType(),
        entity.getIncludedQuestionIds());
  }
}
