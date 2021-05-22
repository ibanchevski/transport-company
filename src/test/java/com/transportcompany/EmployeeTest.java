package com.transportcompany;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmployeeTest {
    Employee employee;

    @BeforeEach
    public void setUp() {
        this.employee = new Employee("Test Employee", Qualification.DRIVER, 1200.00);
    }

    @Test
    public void getName() {
        assertSame(this.employee.getName(), "Test Employee");
    }

    @Test
    public void getQualification() {
        Qualification qualification = this.employee.getQualification();
        assertTrue(qualification == Qualification.DRIVER
                || qualification == Qualification.TRANSPORTER
                || qualification == Qualification.HAZMATDRIVER);
    }

    @Test
    public void getSalary() {
        assert this.employee.getSalary() == 1200.0;
    }

    @Test
    public void rename() {
        String expectedName = "Renamed Test Employee";
        this.employee.rename(expectedName);
        assert this.employee.getName().equals(expectedName);
    }

    @Test
    public void setSalary() {
        double expectedSalry = 0.0;
        this.employee.setSalary(expectedSalry);
        assert this.employee.getSalary() == expectedSalry;
    }

    @Test
    public void setQualification() {
        Qualification expectedQualification = Qualification.TRANSPORTER;
        this.employee.setQualification(expectedQualification);
        assertSame(this.employee.getQualification(), expectedQualification);
    }

    @Test
    public void addFare() {
        Fare fare = new Fare(new Client("Test"), "Sofia", "Varna");
        this.employee.addFare(fare);
        assert this.employee.getFares().contains(fare);
    }

    @Test
    public void addFareIncome() {
        double income = 100.0;
        double before = this.employee.getFaresIncome();
        this.employee.addFareIncome(income);

        assertEquals(before + income, this.employee.getFaresIncome());
    }
}
