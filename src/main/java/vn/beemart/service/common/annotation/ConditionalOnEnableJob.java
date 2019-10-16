package vn.beemart.service.common.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(EnableJobCondition.class)
public @interface ConditionalOnEnableJob { }