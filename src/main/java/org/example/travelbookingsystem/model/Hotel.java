package org.example.travelbookingsystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    private String city;

    private String country;

    private double pricePerNight;

    private int availableRooms;

    @OneToMany(mappedBy="hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    public Hotel() {}

    public Hotel(String name, String city, String country, double pricePerNight, List<Booking> bookings, int availableRooms) {
        this.name = name;
        this.city = city;
        this.country = country;
        this.pricePerNight = pricePerNight;
        this.bookings = bookings;
        this.availableRooms = availableRooms;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", availableRooms=" + availableRooms +
                '}';
    }
}
