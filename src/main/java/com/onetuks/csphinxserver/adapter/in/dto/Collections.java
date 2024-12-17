package com.onetuks.csphinxserver.adapter.in.dto;

import com.onetuks.csphinxserver.domain.question.Collection;
import org.springframework.data.domain.Page;

public record Collections(Page<Collection> collections) {}
