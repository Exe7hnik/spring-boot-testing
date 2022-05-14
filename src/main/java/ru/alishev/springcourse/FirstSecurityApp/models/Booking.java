package ru.alishev.springcourse.FirstSecurityApp.models;

import javax.persistence.*;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_start")
    private int date_start;

    @Column(name = "date_end")
    private int date_end;

    @Column(name = "total_price")
    private int total_price;

    @Column(name = "status")
    private boolean status;


    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person person;

    public Booking() {

    }

    public Booking(int id,
                   int date_start,
                   int date_end,
                   int total_price,
                   boolean status
                   ) {
        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.total_price = total_price;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate_start() {
        return date_start;
    }

    public void setDate_start(int date_start) {
        this.date_start = date_start;
    }

    public int getDate_end() {
        return date_end;
    }

    public void setDate_end(int date_end) {
        this.date_end = date_end;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
