package com.veeker.resubmit.annotations;

import com.veeker.resubmit.aop.ResubmitAspect;
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
@Import(ResubmitAspect.class)
@Documented
public @interface EnableResubmit {
}
