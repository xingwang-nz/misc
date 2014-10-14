package nz.co.xingsoft.gwt.sample.server;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import nz.co.xingsoft.gwt.sample.shared.validation.ValidatedParam;
import nz.co.xingsoft.gwt.sample.shared.validation.exception.ConstraintViolationException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final ValidatorFactory factory = Validation.byDefaultProvider().configure().buildValidatorFactory();

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // @Before("@annotation(nz.co.xingsoft.gwt.sample.shared.validation.ValidatedParam)")
    // @Before("execution(* *(*, @nz.co.xingsoft.gwt.sample.shared.validation.ValidatedParam (*)))")
    @Before("execution(* *(.., @nz.co.xingsoft.gwt.sample.shared.validation.ValidatedParam (*), ..))")
    public void validate(final JoinPoint jp) throws SecurityException, NoSuchMethodException {
        // Get the target method
        final Method interfaceMethod = ((MethodSignature) jp.getSignature()).getMethod();
        final Method implementationMethod = jp.getTarget().getClass().getMethod(interfaceMethod.getName(), interfaceMethod.getParameterTypes());

        final Set<ConstraintViolation<Object>> violations = new HashSet<ConstraintViolation<Object>>();

        // Get the annotated parameters and validate those with the @Valid annotation
        final Annotation[][] annotationParameters = implementationMethod.getParameterAnnotations();
        for (int i = 0; i < annotationParameters.length; i++) {
            final Annotation[] annotations = annotationParameters[i];
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(ValidatedParam.class)) {
                    ValidatedParam valid = (ValidatedParam) annotation;
                    final Validator validator = factory.getValidator();

                    violations.addAll(validator.validate(jp.getArgs()[i], valid.groups()));

                }
            }
        }

        if (violations.size() > 0) {
            throw new ConstraintViolationException(violations);
        }
    }
}
