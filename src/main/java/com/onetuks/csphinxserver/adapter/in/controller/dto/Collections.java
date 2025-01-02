package com.onetuks.csphinxserver.adapter.in.controller.dto;

import com.onetuks.csphinxserver.domain.collection.Collection;
import org.springframework.data.domain.Page;

public record Collections(Page<Collection> collections) {}
