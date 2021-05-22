package com.transportcompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    Vehicle vehicle;
    @BeforeEach
    public void setUp() {
        this.vehicle = new Vehicle(VehicleType.CARGO);
    }

    @Test
    public void getType() {
        assertSame(this.vehicle.getType(), VehicleType.CARGO);
    }
}