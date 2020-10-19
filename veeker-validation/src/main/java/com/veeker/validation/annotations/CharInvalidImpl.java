package com.veeker.validation.annotations;

import com.veeker.core.utils.RegexUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ：qiaoliang
 */
public class CharInvalidImpl implements ConstraintValidator<CharInvalid,Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return RegexUtils.checkChar(o.toString().trim());
    }
}
