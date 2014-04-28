package nz.co.xingsoft.mybatis.persistence.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User
        implements Serializable {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Gender gender;

    private Date birthDay;

    private String nativeTongue;

    private String homeCountry;

    private Date createdTime;

    private List<Phone> phones = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(final Date createdTime) {
        this.createdTime = createdTime;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(final List<Phone> phones) {
        this.phones = phones;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(final Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getNativeTongue() {
        return nativeTongue;
    }

    public void setNativeTongue(final String nativeTongue) {
        this.nativeTongue = nativeTongue;
    }

    public String getHomeCountry() {
        return homeCountry;
    }

    public void setHomeCountry(final String homeCountry) {
        this.homeCountry = homeCountry;
    }

}
