package com.jhc.validation;

import com.jhc.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @description:
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-03-03 17:18
 **/

//ConstraintValidator<给哪个注解提供校验规则，校验的数据类型>
public class StateValidation implements ConstraintValidator<State,String> {
    /** 
    * @Description: 
    * @Param: value 将来要校验的数据
    * @return: 如果返回false，校验不通过；反之
    * @Author: JhcZ
    * @Date: 
    */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if(value == null){
            return false;
        }

        if(value.equals("已发布") || value.equals("草稿")){
            return true;
        }

        return false;
    }
}