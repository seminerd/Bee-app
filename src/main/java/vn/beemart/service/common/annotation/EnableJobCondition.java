package vn.beemart.service.common.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableJobCondition implements Condition {

    private static final String ENABLE_JOB_CONFIG = "BEEMART_ENABLE_JOB_CONFIG";

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Boolean config = conditionContext.getEnvironment().getProperty(ENABLE_JOB_CONFIG, Boolean.class);
        if (config == null)
            return true;
        return true;
    }
}