package nz.co.xingsoft.gwt.sample.client.message;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;

public interface ValidationMessage extends ConstantsWithLookup {
    public static final ValidationMessage INSTANCE = GWT.create(ValidationMessage.class);

    public String requiredFieldMessage();

    public String invalidEmail();
}
