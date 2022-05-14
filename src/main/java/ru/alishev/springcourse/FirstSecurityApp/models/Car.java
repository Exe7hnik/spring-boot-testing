package ru.alishev.springcourse.FirstSecurityApp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "body_type")
    private String body_type;

    @Column(name = "engine_type")
    private String engine_type;

    @Column(name = "horse_power")
    private int horse_power;

    @Column(name = "price_per_day")
    private int price_per_day;

    @Column(name = "description")
    private String description;

    @Column(name = "view_or_not")
    private boolean view_or_not;

    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;

    public Car() {

    }



    public Car(
               int id,
               String brand,
               String model,
               String color,
               String body_type,
               String engine_type,
               int horse_power,
               int price_per_day,
               String description,
               boolean view_or_not
    ) {

        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.body_type = body_type;
        this.engine_type = engine_type;
        this.horse_power = horse_power;
        this.price_per_day = price_per_day;
        this.description = description;
        this.view_or_not = view_or_not;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBody_type() {
        return body_type;
    }

    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public int getHorse_power() {
        return horse_power;
    }

    public void setHorse_power(int horse_power) {
        this.horse_power = horse_power;
    }

    public int getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(int price_per_day) {
        this.price_per_day = price_per_day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isView_or_not() {
        return view_or_not;
    }

    public void setView_or_not(boolean view_or_not) {
        this.view_or_not = view_or_not;
    }


}

