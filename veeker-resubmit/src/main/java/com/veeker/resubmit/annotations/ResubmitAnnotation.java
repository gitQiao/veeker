package com.veeker.resubmit.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 *  重复提交注解
 *
 * @author ：qiaoliang
 * @date 2020-09-11 11:21
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ResubmitAnnotation {

    String businessType() default "system";

    long resubmitTime() default 5L;

    String errorMessage() default "重复请求,请稍候重试.";

}
