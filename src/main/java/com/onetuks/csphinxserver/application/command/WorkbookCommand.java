package com.onetuks.csphinxserver.application.command;

import com.onetuks.csphinxserver.domain.workbook.CollectionType;
import java.util.List;

public record WorkbookCommand(
    String title,
    String description,
    CollectionType collectionType,
    List<Long> includedProblemIds,
    Boolean isActive) {}
