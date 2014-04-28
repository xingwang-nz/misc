package nz.co.xingsoft.mybatis.persistence.domain;

import java.io.Serializable;

public class Phone
        implements Serializable {

    private Long id;

    private PhoneType phoneType;

    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(final PhoneType phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
