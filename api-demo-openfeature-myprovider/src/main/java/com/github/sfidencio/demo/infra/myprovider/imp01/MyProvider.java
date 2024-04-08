package com.github.sfidencio.demo.infra.myprovider.imp01;

import dev.openfeature.sdk.*;
import dev.openfeature.sdk.exceptions.*;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MyProvider extends EventProvider {

    private Map<String, Object> dataBase;

    private static final String NAME = "MyProvider";

    private Map<String, MyCustomFlag<?>> flags;

    @Getter
    private ProviderState state = ProviderState.NOT_READY;

    private EvaluationContext evaluationContext;

    @Override
    public Metadata getMetadata() {
        return () -> NAME;
    }

    public MyProvider() {
        //data0
        Map<String, Object> state0 = Map.of("state", "ENABLED");
        Map<String, Object> variants0 = Map.of("on", true, "off", false);
        Map<String, Object> defaultVariants0 = Map.of("defaultVariant", "on");
        Map<String, Object> data0 = Map.of("variants", variants0, "defaultVariants", defaultVariants0, "state", state0);
        //data1
        Map<String, Object> state1 = Map.of("state", "DISABLED");
        Map<String, Object> variants1 = Map.of("on", true, "off", false);
        Map<String, Object> defaultVariants1 = Map.of("defaultVariant", "off");
        Map<String, Object> data1 = Map.of("variants", variants1, "defaultVariants", defaultVariants1, "state", state1);

        //data2
        Map<String, Object> state2 = Map.of("state", "ENABLED");
        Map<String, Object> variants2 = Map.of("ligado", true, "desligado", false);
        Map<String, Object> defaultVariants2 = Map.of("defaultVariant", "ligado");
        Map<String, Object> data2 = Map.of("variants", variants2, "defaultVariants", defaultVariants2, "state", state2);

        this.dataBase = Map.of("feature0", data0, "feature1", data1, "feature2", data2);


        this.flags = new HashMap<>();
        //feature1 = {defaultVariants={defaultVariant=on}, variants={on=true, off=false}}
        //feature2 = {defaultVariants={defaultVariant=off}, variants={on=true, off=false}}

        this.dataBase.forEach((key, value) -> {
            Map<String, Object> data = (Map<String, Object>) value;
            Map<String, Object> variants = (Map<String, Object>) data.get("variants");
            Map<String, Object> defaultVariants = (Map<String, Object>) data.get("defaultVariants");
            Map<String, Object> state = (Map<String, Object>) data.get("state");




            this.flags.put(key, MyCustomFlag.builder()
                    .variants(variants)
                    .defaultVariant((String) defaultVariants.get("defaultVariant"))
                    .state((String) (state.get("state")))
                    .build());

        });

        System.out.println("flags: " + this.flags);
    }

    /**
     * Initialize the provider.
     *
     * @param evaluationContext evaluation context
     * @throws Exception on error
     */
    @Override
    public void initialize(EvaluationContext evaluationContext) throws Exception {
        super.initialize(evaluationContext);
        state = ProviderState.READY;
        log.debug("finished initializing provider, state: {}", state);
    }

    /**
     * Updating provider flags configuration, replacing existing flags.
     *
     * @param flags the flags to use instead of the previous flags.
     */
    public void updateFlags(Map<String, MyCustomFlag<?>> flags) {
        Set<String> flagsChanged = new HashSet<>();
        flagsChanged.addAll(this.flags.keySet());
        flagsChanged.addAll(flags.keySet());
        this.flags = new HashMap<>(flags);
        ProviderEventDetails details = ProviderEventDetails.builder()
                .flagsChanged(new ArrayList<>(flagsChanged))
                .message("flags changed")
                .build();
        emitProviderConfigurationChanged(details);
    }

    /**
     * Updating provider flags configuration with adding or updating a flag.
     *
     * @param flag the flag to update. If a flag with this key already exists, new flag replaces it.
     */
    public void updateFlag(String flagKey, MyCustomFlag<?> flag) {
        this.flags.put(flagKey, flag);
        ProviderEventDetails details = ProviderEventDetails.builder()
                .flagsChanged(Collections.singletonList(flagKey))
                .message("flag added/updated")
                .build();
        emitProviderConfigurationChanged(details);
    }

    @Override
    public ProviderEvaluation<Boolean> getBooleanEvaluation(String key, Boolean defaultValue,
                                                            EvaluationContext evaluationContext) {
        return getEvaluation(key, evaluationContext, Boolean.class);
    }

    @Override
    public ProviderEvaluation<String> getStringEvaluation(String key, String defaultValue,
                                                          EvaluationContext evaluationContext) {
        return getEvaluation(key, evaluationContext, String.class);
    }

    @Override
    public ProviderEvaluation<Integer> getIntegerEvaluation(String key, Integer defaultValue,
                                                            EvaluationContext evaluationContext) {
        return getEvaluation(key, evaluationContext, Integer.class);
    }

    @Override
    public ProviderEvaluation<Double> getDoubleEvaluation(String key, Double defaultValue,
                                                          EvaluationContext evaluationContext) {
        return getEvaluation(key, evaluationContext, Double.class);
    }

    @SneakyThrows
    @Override
    public ProviderEvaluation<Value> getObjectEvaluation(String key, Value defaultValue,
                                                         EvaluationContext evaluationContext) {
        return getEvaluation(key, evaluationContext, Value.class);
    }

    private <T> ProviderEvaluation<T> getEvaluation(
            String key, EvaluationContext evaluationContext, Class<?> expectedType
    ) throws OpenFeatureError {
        if (!ProviderState.READY.equals(state)) {
            if (ProviderState.NOT_READY.equals(state)) {
                throw new ProviderNotReadyError("provider not yet initialized");
            }
            throw new GeneralError("unknown error");
        }
        MyCustomFlag<?> flag = flags.get(key);
        if (flag == null) {
            throw new FlagNotFoundError("flag " + key + "not found");
        }
        T value;
        if (flag.getContextEvaluator() != null) {
            value = (T) flag.getContextEvaluator().evaluate(flag, evaluationContext);
        } else if (!expectedType.isInstance(flag.getVariants().get(flag.getDefaultVariant()))) {
            throw new TypeMismatchError("flag " + key + "is not of expected type");
        } else {
            value = (T) flag.getVariants().get(flag.getDefaultVariant());
        }
        return ProviderEvaluation.<T>builder()
                .value(value)
                .variant(flag.getDefaultVariant())
                .reason(Reason.STATIC.toString())
                .build();
    }

}

