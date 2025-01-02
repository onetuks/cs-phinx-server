package com.onetuks.csphinxserver.application.command.collection;

import com.onetuks.csphinxserver.domain.collection.CollectionType;
import java.util.Set;

public record CollectionAddCommand(
    String collectionName, CollectionType collectionType, Set<String> includedQuestionIds) {}
