package com.github.sfidencio.demo.infra.myprovider.imp01;

import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.providers.memory.ContextEvaluator;

public interface ContextEvaluatorCustom<T> extends ContextEvaluator<T> {
    T evaluate(MyCustomFlag flag, EvaluationContext evaluationContext);
}
