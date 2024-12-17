package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.application.port.in.CollectionUseCases;
import com.onetuks.csphinxserver.application.port.out.CollectionPort;
import com.onetuks.csphinxserver.domain.question.Collection;
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
}
