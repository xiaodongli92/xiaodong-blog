package com.xiaodong.blog.model;

import javax.persistence.*;

/**
 * Created by xiaodong on 2015/11/16.
 */
@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
    private long id = 0 ;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
