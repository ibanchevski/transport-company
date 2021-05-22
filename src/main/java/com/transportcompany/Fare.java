package com.transportcompany;

import java.time.LocalDateTime;

public class Fare {
    private String startDestination;
    private String endDestination;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
//    private boolean isTransportingPeople;
    private FareType goodsType;
    private Client client;
    private double price;
    private boolean completed;
    private Employee driver;

    public Fare(Client client, String startDestination, String endDestination) {
        this.client = client;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.completed = false;
    }

    public enum FareType { PEOPLE,CARGO,FLAMMABLE }

    public String getStartDestination() { return this.startDestination; }
    public String getEndDestination() { return this.endDestination; }
    public LocalDateTime getStartDate() { return this.startDate; }
    public LocalDateTime getEndDate() { return this.endDate; }
    public Client getClient() { return this.client; }
    public double getPrice() { return this.price; }
    public Employee getDriver() { return this.driver; }
    public String getStatus() {
        if (this.completed) {
            return "Completed";
        }
        return "Pending";
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setGoodsType(FareType goodsType) {
        this.goodsType = goodsType;
    }

    public FareType getGoodsType() {
        return this.goodsType;
    }

    public void setDriver(Employee driver) throws EmployeeException {
        Qualification driverQualification = driver.getQualification();

        if (this.goodsType == FareType.PEOPLE && driverQualification != Qualification.TRANSPORTER) {
            throw new EmployeeException("Driver must have TRANSPORTER qualification to complete this fare!");
        }

        if (this.goodsType == FareType.FLAMMABLE && driverQualification != Qualification.HAZMATDRIVER) {
            throw new EmployeeException("Driver must have HAZMATDRIVER qualification to complete this fare!");
        }

        if (this.goodsType == FareType.CARGO && driverQualification != Qualification.DRIVER) {
            throw new EmployeeException("Driver must have DRIVER qualification to complete this fare!");
        }

        this.driver = driver;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String fare = "<Fare(" + this.client
                + ", " + this.startDestination + "-" + this.endDestination + ", "
                + "(" + this.startDestination + ":" + this.endDate + "), "
                + this.price;

        if (this.completed) {
            fare += " - COMPLETED";
        } else {
            fare += " - PENDING";
        }

        return fare;

    }
}
