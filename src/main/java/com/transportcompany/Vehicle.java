package com.transportcompany;

public class Vehicle implements Comparable<Vehicle> {
    private final VehicleType type;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() { return this.type; }

    @Override
    public int compareTo(Vehicle vehicle) {
        return 0;
    }

    @Override
    public String toString() {
        return "<Vehicle>(" + this.type + ")>";
    }
}
