package com.onetuks.csphinxserver.application.port.in;

import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.domain.question.Collection;

public interface CollectionUseCases {

  Collection addCollection(CollectionAddCommand command);
}
