package com.onetuks.csphinxserver.application;

import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.application.port.in.CollectionUseCases;
import com.onetuks.csphinxserver.application.port.out.CollectionPort;
import com.onetuks.csphinxserver.domain.question.Collection;
import org.springframework.stereotype.Service;

@Service
public class CollectionService implements CollectionUseCases {

  private final CollectionPort collectionPort;

  public CollectionService(CollectionPort collectionPort) {
    this.collectionPort = collectionPort;
  }

  @Override
  public Collection addCollection(CollectionAddCommand command) {
    return collectionPort.create(
        new Collection(null,
            command.collectionName(),
            command.collectionType(),
            command.includedQuestionIds()));
  }
}
