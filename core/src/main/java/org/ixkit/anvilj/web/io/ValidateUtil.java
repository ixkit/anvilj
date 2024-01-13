package org.ixkit.anvilj.web.io;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateUtil {
    public static <T> List<String> validate(T t){
        Validator validatorFactory = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validatorFactory.validate(t);

        return errors.stream().map(error -> error.getMessage()).collect(Collectors.toList());
    }

}
