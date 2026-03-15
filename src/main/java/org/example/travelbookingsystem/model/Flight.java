package org.example.travelbookingsystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String origin;

    private String destination;

    private LocalDateTime departure;

    private LocalDateTime arrival;

    private double price;

    private int availableSeats;

    @OneToMany(mappedBy="flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    public Flight() {}

    public Flight(String origin, String destination, LocalDateTime departure, LocalDateTime arrival, double price, List<Booking> bookings, int availableSeats) {
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
        this.price = price;
        this.bookings = bookings;
        this.availableSeats = availableSeats;
    }

    public long getId() {
        return id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", price=" + price +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
