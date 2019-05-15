package com.ltt.logintest.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "staff")
public class Staff {
    private long id;
    private String name;
    private Date birthday;
    private String phonenumber;
    private String address;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //very important when you have date type in your database. REMEMBER IT PLZZZZ
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "phonenumber")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id &&
                Objects.equals(name, staff.name) &&
                Objects.equals(birthday, staff.birthday) &&
                Objects.equals(phonenumber, staff.phonenumber) &&
                Objects.equals(address, staff.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday, phonenumber, address);
    }
}
