package com.onetuks.csphinxserver.domain.question;

import java.util.Set;

public record Collection(
    String collectionId,
    String collectionName,
    CollectionType collectionType,
    Set<String> includedQuestionIds
) {}
