package nz.co.xingsoft.gwt.sample.shared.dto;

import nz.co.xingsoft.gwt.sample.shared.validation.Validatable;
import nz.co.xingsoft.gwt.sample.shared.validation.constraint.Email;
import nz.co.xingsoft.gwt.sample.shared.validation.constraint.NotBlank;
import nz.co.xingsoft.gwt.sample.shared.validation.constraint.SelfValidating;
import nz.co.xingsoft.gwt.sample.shared.validation.group.ValidationGroup1;
import nz.co.xingsoft.gwt.sample.shared.validation.group.ValidationGroup2;

import com.google.common.base.Strings;

@SelfValidating(message = "Either mobile or homePhone should be provided", groups = { ValidationGroup2.class })
public class UserDto implements Validatable {

    private static final long serialVersionUID = 2804928958193771272L;

    @NotBlank(message = "name required", groups = { ValidationGroup1.class })
    private String name;

    @Email(message = "email invalid", groups = { ValidationGroup1.class, ValidationGroup2.class })
    private String email;

    private String mobile;

    private String homePhone;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDto [name=");
        builder.append(name);
        builder.append(", email=");
        builder.append(email);
        builder.append(", mobile=");
        builder.append(mobile);
        builder.append("]");
        return builder.toString();
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(final String homePhone) {
        this.homePhone = homePhone;
    }

    @Override
    public boolean isValid() {
        // TODO: can be delegate to custom validator
        return !Strings.isNullOrEmpty(mobile) || !Strings.isNullOrEmpty(homePhone);
    }

}
