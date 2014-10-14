package nz.co.xingsoft.gwt.sample.shared.validation.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import nz.co.xingsoft.gwt.sample.shared.validation.Validatable;

public class SelfValidatingValidator implements ConstraintValidator<SelfValidating, Validatable> {

    @Override
    public void initialize(final SelfValidating constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Validatable value, final ConstraintValidatorContext context) {
        return value.isValid();
    }

}
