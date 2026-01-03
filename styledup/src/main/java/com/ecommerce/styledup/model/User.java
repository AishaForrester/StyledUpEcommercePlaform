package com.ecommerce.styledup.model;

import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;  // Change from javax to jakarta
import jakarta.persistence.Entity;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="role")
     private String role;
     
    private String username;
    private String password;
    private String email;
   


    public void setUsername(String u) {
        this.username = u;
    }
    public void setEmail(String e) {
        this.email = e;
    }

    public void setPassword(String p) {
        this.password = p ;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public Integer getId() {
        return id;
    }
    public String getRole() {
        return role;
    }

    

}



