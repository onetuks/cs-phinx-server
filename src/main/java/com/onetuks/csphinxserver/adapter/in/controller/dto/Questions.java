package com.onetuks.csphinxserver.adapter.in.controller.dto;

import com.onetuks.csphinxserver.domain.question.Question;
import org.springframework.data.domain.Page;

public record Questions(Page<Question> questions) {}