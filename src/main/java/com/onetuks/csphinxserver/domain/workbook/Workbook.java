package com.onetuks.csphinxserver.domain.workbook;

import com.onetuks.csphinxserver.domain.problem.Problem;
import java.time.LocalDateTime;
import java.util.List;

public record Workbook(
    Long workbookId,
    String title,
    String description,
    CollectionType collectionType,
    List<Problem> includedProblems,
    boolean isActive,
    LocalDateTime updatedAt) {}
