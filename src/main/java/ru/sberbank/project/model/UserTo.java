package ru.sberbank.project.model;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserTo implements Serializable, HasId {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String name;

    @NotBlank
    @Size(min = 2, max = 100)
    @SafeHtml
    private String lastName;

    @Email
    @NotBlank
    @Size(max = 100)
    @SafeHtml
    private String email;

    @Size(min = 5, max = 32)
    private String password;

    @Range(min = 1, max = 31)
    private int birthdayDay;

    @Range(min = 1, max = 12)
    private int birthdayMonth;

    @Range(min = 1900, max = 2018)
    private int birthdayYear;

    private int currentYear = LocalDate.now().getYear();

    public UserTo() {
    }

    public UserTo(Integer id, String name, String lastName, String email, String password, LocalDateTime birthday) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthdayDay = birthday.getDayOfMonth();
        this.birthdayMonth = birthday.getMonthValue();
        this.birthdayYear = birthday.getYear();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(int birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public int getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(int birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdayDay=" + birthdayDay +
                ", birthdayMounth=" + birthdayMonth +
                ", birthdayYear=" + birthdayYear +
                '}';
    }
}
