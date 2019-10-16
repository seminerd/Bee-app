package vn.beemart.service.common.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableOrderJobCondition implements Condition {

    private static final String ENABLE_ORDER_JOB_CONFIG = "BEEMART_ENABLE_ORDER_JOB_CONFIG";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Boolean config = context.getEnvironment().getProperty(ENABLE_ORDER_JOB_CONFIG, Boolean.class);
        if (config == null)
            return false;
        return config.booleanValue();
    }
}
