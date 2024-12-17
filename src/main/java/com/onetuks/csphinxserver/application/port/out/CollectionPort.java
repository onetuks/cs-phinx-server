package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.question.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionPort {

  Collection create(Collection collection);

  Collection read(String collectionId);

  void update(Collection collection);

  void delete(String collectionId);
}
