package com.jhc.anno;

import com.jhc.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class})  //指定提供校验规则的类
public @interface State {
    //提供校验失败时的提示信息
    String message() default "state的值只能是已发布或草稿";

    //指定分组
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
