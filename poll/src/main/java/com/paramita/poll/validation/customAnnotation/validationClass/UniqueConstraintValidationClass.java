package com.paramita.poll.validation.customAnnotation.validationClass;

import com.paramita.poll.exception.UniqueConstraintException;
import com.paramita.poll.to.OptionTO;
import com.paramita.poll.validation.customAnnotation.UniqueConstraintValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;

public class UniqueConstraintValidationClass implements ConstraintValidator<UniqueConstraintValidation, List<OptionTO>>{

    @Override
    public boolean isValid(List<OptionTO> optionList, ConstraintValidatorContext context) {
        if(optionList == null || optionList.isEmpty()){
            return true;
        }
        optionList.stream().map(OptionTO::getOption).collect(Collectors.groupingBy(e->e,Collectors.counting())).entrySet().stream()
                .forEach(e->{
                    if(e.getValue()>1){
                        throw new UniqueConstraintException("Option should be unique");
                    }
                });
        return true;
    }
}
