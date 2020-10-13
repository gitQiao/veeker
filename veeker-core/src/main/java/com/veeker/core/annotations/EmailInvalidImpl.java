package com.veeker.core.annotations;


import com.veeker.core.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ：qiaoliang
 */
public class EmailInvalidImpl implements ConstraintValidator<EmailInvalid,Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.matchEmail(o.toString().trim());
    }
}
