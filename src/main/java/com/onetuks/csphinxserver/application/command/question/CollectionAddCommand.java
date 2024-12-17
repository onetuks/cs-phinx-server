package com.onetuks.csphinxserver.application.command.question;

import com.onetuks.csphinxserver.domain.question.CollectionType;
import java.util.Set;

public record CollectionAddCommand(
    String collectionName, CollectionType collectionType, Set<String> includedQuestionIds) {}
