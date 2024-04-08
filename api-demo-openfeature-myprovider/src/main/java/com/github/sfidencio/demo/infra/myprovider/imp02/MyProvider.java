package com.github.sfidencio.demo.infra.myprovider.imp02;

import dev.openfeature.sdk.*;
import dev.openfeature.sdk.exceptions.GeneralError;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class MyProvider extends EventProvider {

    @Getter
    private static final String NAME = "Flipt";
    public static final String PROVIDER_NOT_YET_INITIALIZED = "provider not yet initialized";
    public static final String UNKNOWN_ERROR = "unknown error";

    //@Getter(AccessLevel.PROTECTED)
    //oprivate FliptProviderConfig fliptProviderConfig;

    //@Setter(AccessLevel.PROTECTED)
    //@Getter
    //private FliptClient fliptClient;

    @Setter(AccessLevel.PROTECTED)
    @Getter
    private ProviderState state = ProviderState.NOT_READY;

    private AtomicBoolean isInitialized = new AtomicBoolean(false);

    /**
     * Constructor.
     *
     * @param fliptProviderConfig FliptProviderConfig
     */
    /*public MyProvider(FliptProviderConfig fliptProviderConfig) {
        this.fliptProviderConfig = fliptProviderConfig;
    }*/


    public MyProvider() {
    }

    /**
     * Initialize the provider.
     *
     * @param evaluationContext evaluation context
     * @throws Exception on error
     */
    @Override
    public void initialize(EvaluationContext evaluationContext) throws Exception {
        boolean initialized = isInitialized.getAndSet(true);
        if (initialized) {
            throw new GeneralError("already initialized");
        }
        super.initialize(evaluationContext);
        //fliptClient = fliptProviderConfig.getFliptClientBuilder().build();

        state = ProviderState.READY;
        log.info("finished initializing provider, state: {}", state);
    }

    @Override
    public Metadata getMetadata() {
        return () -> NAME;
    }

    @Override
    public void emitProviderReady(ProviderEventDetails details) {
        super.emitProviderReady(details);
        state = ProviderState.READY;
    }

    @Override
    public void emitProviderError(ProviderEventDetails details) {
        super.emitProviderError(details);
        state = ProviderState.ERROR;
    }

    @Override
    public ProviderEvaluation<Boolean> getBooleanEvaluation(String key, Boolean defaultValue, EvaluationContext ctx) {
        /*if (!ProviderState.READY.equals(state)) {
            if (ProviderState.NOT_READY.equals(state)) {
                throw new ProviderNotReadyError(PROVIDER_NOT_YET_INITIALIZED);
            }
            throw new GeneralError(UNKNOWN_ERROR);
        }

        Map<String, String> contextMap = ContextTransformer.transform(ctx);
        EvaluationRequest request = EvaluationRequest.builder().namespaceKey(fliptProviderConfig.getNamespace())
                .flagKey(key).entityId(ctx.getTargetingKey()).context(contextMap).build();

        BooleanEvaluationResponse response = null;
        try {
            response = fliptClient.evaluation().evaluateBoolean(request);
        } catch (Exception e) {
            log.error("Error evaluating boolean", e);
            throw new GeneralError(e.getMessage());
        }

        return ProviderEvaluation.<Boolean>builder()
                .value(response.isEnabled())
                .reason(response.getReason().toString())
                .build();*/

        return null;
    }

    @Override
    public ProviderEvaluation<String> getStringEvaluation(String key, String defaultValue, EvaluationContext ctx) {
        ProviderEvaluation<Value> valueProviderEvaluation = getObjectEvaluation(key, new Value(defaultValue), ctx);
        return ProviderEvaluation.<String>builder()
                .value(valueProviderEvaluation.getValue().asString())
                .variant(valueProviderEvaluation.getVariant())
                .errorCode(valueProviderEvaluation.getErrorCode())
                .reason(valueProviderEvaluation.getReason())
                .flagMetadata(valueProviderEvaluation.getFlagMetadata())
                .build();
    }

    @Override
    public ProviderEvaluation<Integer> getIntegerEvaluation(String key, Integer defaultValue, EvaluationContext ctx) {
        ProviderEvaluation<Value> valueProviderEvaluation = getObjectEvaluation(key, new Value(defaultValue), ctx);
        Integer value = getIntegerValue(valueProviderEvaluation, defaultValue);
        return ProviderEvaluation.<Integer>builder()
                .value(value)
                .variant(valueProviderEvaluation.getVariant())
                .errorCode(valueProviderEvaluation.getErrorCode())
                .reason(valueProviderEvaluation.getReason())
                .flagMetadata(valueProviderEvaluation.getFlagMetadata())
                .build();
    }

    private static Integer getIntegerValue(ProviderEvaluation<Value> valueProviderEvaluation, Integer defaultValue) {
        String valueStr = valueProviderEvaluation.getValue().asObject().toString();
        try {
            return Integer.parseInt(valueStr);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    @Override
    public ProviderEvaluation<Double> getDoubleEvaluation(String key, Double defaultValue, EvaluationContext ctx) {
        ProviderEvaluation<Value> valueProviderEvaluation = getObjectEvaluation(key, new Value(defaultValue), ctx);
        Double value = getDoubleValue(valueProviderEvaluation, defaultValue);
        return ProviderEvaluation.<Double>builder()
                .value(value)
                .variant(valueProviderEvaluation.getVariant())
                .errorCode(valueProviderEvaluation.getErrorCode())
                .reason(valueProviderEvaluation.getReason())
                .flagMetadata(valueProviderEvaluation.getFlagMetadata())
                .build();
    }

    private static Double getDoubleValue(ProviderEvaluation<Value> valueProviderEvaluation, Double defaultValue) {
        String valueStr = valueProviderEvaluation.getValue().asObject().toString();
        try {
            return Double.parseDouble(valueStr);
        } catch (NumberFormatException ex) {
            return defaultValue;
        }
    }

    @Override
    public ProviderEvaluation<Value> getObjectEvaluation(String key, Value defaultValue, EvaluationContext ctx) {
        /*if (!ProviderState.READY.equals(state)) {
            if (ProviderState.NOT_READY.equals(state)) {
                throw new ProviderNotReadyError(PROVIDER_NOT_YET_INITIALIZED);
            }
            throw new GeneralError(UNKNOWN_ERROR);
        }
        Map<String, String> contextMap = ContextTransformer.transform(ctx);
        EvaluationRequest request = EvaluationRequest.builder().namespaceKey(fliptProviderConfig.getNamespace())
                .flagKey(key).entityId(ctx.getTargetingKey()).context(contextMap).build();

        VariantEvaluationResponse response;
        try {
            response = fliptClient.evaluation().evaluateVariant(request);
        } catch (Exception e) {
            log.error("Error evaluating variant", e);
            throw new GeneralError(e.getMessage());
        }

        if (!response.isMatch()) {
            log.debug("non matching variant for {} : {}", key, response.getReason());
            return ProviderEvaluation.<Value>builder()
                    .value(defaultValue)
                    .reason(DEFAULT.name())
                    .build();
        }

        ImmutableMetadata.ImmutableMetadataBuilder flagMetadataBuilder = ImmutableMetadata.builder();
        if (response.getVariantAttachment() != null) {
            flagMetadataBuilder.addString("variant-attachment", response.getVariantAttachment());
        }

        return ProviderEvaluation.<Value>builder()
                .value(new Value(response.getVariantKey()))
                .variant(response.getVariantKey())
                .reason(TARGETING_MATCH.name())
                .flagMetadata(flagMetadataBuilder.build())
                .build();*/
        return null;
    }

    @Override
    public void shutdown() {
        super.shutdown();
        log.info("shutdown");
        state = ProviderState.NOT_READY;
    }
}
