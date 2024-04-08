package com.github.sfidencio.demo.infra.myprovider.imp01;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Map;

@ToString
@Builder
@Getter
public class MyCustomFlag<T> {
    @Singular
    private Map<String, Object> variants;
    private String defaultVariant;
    private String state;
    private ContextEvaluatorCustom<T> contextEvaluator;
}
