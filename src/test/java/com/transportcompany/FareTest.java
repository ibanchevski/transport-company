package com.transportcompany;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FareTest {
    Fare fare;

    @BeforeEach
    public void setUp() {
        this.fare = new Fare(new Client("Test client"), "Sofia", "Varna");
    }

    @Test
    void getStartDestination() {
        assertEquals("Sofia", this.fare.getStartDestination());
    }

    @Test
    void getEndDestination() {
        assertEquals("Varna", this.fare.getEndDestination());
    }

    @Test
    void getStartDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startDate = LocalDateTime.parse("22-02-2021 12:50", dateTimeFormatter);
        this.fare.setStartDate(startDate);
        assertEquals(startDate, this.fare.getStartDate());
    }

    @Test
    void getEndDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime endDate = LocalDateTime.parse("22-02-2021 14:50", dateTimeFormatter);
        this.fare.setEndDate(endDate);
        assertEquals(endDate, this.fare.getEndDate());
    }

    @Test
    void getClient() {
        Client cli = new Client("Test");
        Fare f = new Fare(cli, "Sofia", "Varna");
        assertEquals(cli, f.getClient());
    }

    @Test
    void getPrice() {
        assertEquals(0.0, this.fare.getPrice());
    }

    @Test
    void getDriver() throws EmployeeException {
        Employee driver = new Employee("Test Test", Qualification.DRIVER, 1200);
        this.fare.setDriver(driver);
        assertEquals(driver, this.fare.getDriver());
    }

    @Test
    void getStatus() {
        assertEquals("Pending", this.fare.getStatus());
    }


    @Test
    void setGoodsType() {
        Fare.FareType goodsType = Fare.FareType.PEOPLE;
        this.fare.setGoodsType(goodsType);
        assertEquals(goodsType, this.fare.getGoodsType());
    }

    @Test
    void setPrice() {
        this.fare.setPrice(100.0);
        assertEquals(100.0, this.fare.getPrice());
    }

    @Test
    void complete() {
        this.fare.complete();
        assertSame("Completed", this.fare.getStatus());
    }

    @Test
    void testRepr() {
        assertNotSame("", this.fare.toString());
    }
}