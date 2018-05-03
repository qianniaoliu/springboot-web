package com.athena.px.springbootweb.validation;

import com.athena.px.springbootweb.annotation.ValidUserName;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/24 16:49
 */
public class ValidUserNameConstraint implements ConstraintValidator<ValidUserName,String> {

    @Override
    public void initialize(ValidUserName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String[] paras = StringUtils.delimitedListToStringArray(value,"-");
        if(paras.length != 2){
            return false;
        }
        return true;
    }
}
