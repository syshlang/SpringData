/*
 * Copyright (c) 2018. GRGBanking
 * @File: Person.java
 * @Description:
 * @Author: sunys
 * @Date: 18-6-7 下午9:54
 * @since:
 */

package com.syshlang.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name="JPA_PERSONS")
@Entity
public class Person {

    private Integer id;
    private String lastName;

    private String email;
    private Date birth;


    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", lastName=" + lastName + ", email="
                + email + ", brith=" + birth + "]";
    }
}
