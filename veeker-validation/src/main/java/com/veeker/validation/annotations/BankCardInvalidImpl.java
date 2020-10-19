package com.veeker.validation.annotations;

import com.veeker.core.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ：qiaoliang
 */
public class BankCardInvalidImpl implements ConstraintValidator<BankCardInvalid,Object> {
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.matchBankCard(o.toString().trim());
    }
}
