package ru.alishev.springcourse.FirstSecurityApp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "phone")
    private long phone;

    @Column(name = "email")
    private String email;

    @Column(name = "pass_id")
    private int pass_id;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "person")
    private List<Booking> bookings;

    // Конструктор по умолчанию нужен для Spring
    public Person() {
    }

    public Person(
            int id,
            String username,
            String full_name,
            long phone,
            String email,
            int pass_id,
            String password,
            String role
    ) {
        this.id = id;
        this.username = username;
        this.full_name = full_name;
        this.phone = phone;
        this.email = email;
        this.pass_id = pass_id;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPass_id() {
        return pass_id;
    }

    public void setPass_id(int pass_id) {
        this.pass_id = pass_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", full_name='" + full_name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", pass_id='" + pass_id + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}