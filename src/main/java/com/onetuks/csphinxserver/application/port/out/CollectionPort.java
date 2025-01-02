package com.onetuks.csphinxserver.application.port.out;

import com.onetuks.csphinxserver.domain.collection.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionPort {

  Collection create(Collection collection);

  Collection read(String collectionId);

  Page<Collection> readAll(Pageable pageable);

  void update(Collection collection);

  void delete(String collectionId);
}
