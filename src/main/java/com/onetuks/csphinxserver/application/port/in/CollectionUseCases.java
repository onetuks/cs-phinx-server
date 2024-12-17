package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.application.command.question.CollectionEditCommand;
import com.onetuks.csphinxserver.domain.question.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CollectionUseCases {

  Collection addCollection(CollectionAddCommand command);

  Collection searchCollection(String collectionId);

  Page<Collection> searchAllCollections(Pageable pageable);

  void editCollection(String collectionId, CollectionEditCommand command);

  void removeCollection(String collectionId);
}
