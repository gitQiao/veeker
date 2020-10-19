package com.veeker.validation.annotations;

import com.veeker.validation.handler.ExceptionAdviceHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ：qiaoliang
 * @date ：2020-09-11
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ExceptionAdviceHandler.class)
@Documented
public @interface EnableExceptionAdvice {
}
