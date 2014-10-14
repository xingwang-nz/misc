package nz.co.xingsoft.gwt.sample.shared.validation.exception;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

public class ConstraintViolationException extends ValidationException implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5419651470423571496L;
    private Set<ConstraintViolation<Object>> constraintViolations;

    public ConstraintViolationException() {
        constraintViolations = new HashSet<ConstraintViolation<Object>>();
    }

    /**
     * Creates a constraint violation report
     * 
     * @param message
     *            error message
     * @param constraintViolations
     *            <code>Set</code> of <code>ConstraintViolation</code>
     */
    public ConstraintViolationException(final String message, final Set<ConstraintViolation<Object>> constraintViolations) {
        super(message);
        this.constraintViolations = constraintViolations;
    }

    /**
     * Creates a constraint violation report
     * 
     * @param constraintViolations
     *            <code>Set</code> of <code>ConstraintViolation</code>
     */
    public ConstraintViolationException(final Set<ConstraintViolation<Object>> constraintViolations) {
        super();
        this.constraintViolations = constraintViolations;
    }

    /**
     * Set of constraint violations reported during a validation
     * 
     * @return <code>Set</code> of <code>ConstraintViolation</code>
     */
    public Set<ConstraintViolation<Object>> getConstraintViolations() {
        return constraintViolations;
    }
}
