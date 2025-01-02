package com.onetuks.csphinxserver.domain.collection;

import java.util.Set;

public record Collection(
    String collectionId,
    String collectionName,
    CollectionType collectionType,
    Set<String> includedQuestionIds) {}
