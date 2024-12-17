package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.application.command.question.CollectionEditCommand;
import com.onetuks.csphinxserver.application.port.in.CollectionUseCases;
import com.onetuks.csphinxserver.application.port.out.CollectionPort;
import com.onetuks.csphinxserver.domain.question.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectionService implements CollectionUseCases {

  private final CollectionPort collectionPort;

  public CollectionService(CollectionPort collectionPort) {
    this.collectionPort = collectionPort;
  }

  @Override
  @Transactional
  public Collection addCollection(CollectionAddCommand command) {
    return collectionPort.create(
        new Collection(
            null,
            command.collectionName(),
            command.collectionType(),
            command.includedQuestionIds()));
  }

  @Override
  @Transactional(readOnly = true)
  public Collection searchCollection(String collectionId) {
    return collectionPort.read(collectionId);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<Collection> searchAllCollections(Pageable pageable) {
    return collectionPort.readAll(pageable);
  }

  @Override
  @Transactional
  public void editCollection(String collectionId, CollectionEditCommand command) {
    collectionPort.update(
        new Collection(
            collectionId,
            command.collectionName(),
            command.collectionType(),
            command.includedQuestionIds()));
  }
}
