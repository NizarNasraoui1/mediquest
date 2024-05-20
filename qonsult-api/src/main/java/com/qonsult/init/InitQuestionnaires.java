package com.qonsult.init;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitQuestionnaires{

    private final InitDenstistQuestionnaire initDenstistQuestionnaire;

    @Async
    public void init() {
        initDenstistQuestionnaire.init();
    }
}
