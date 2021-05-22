package com.transportcompany;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportCompanyTest {
    TransportCompany company;
    @BeforeEach
    void setUp() {
        this.company = new TransportCompany("Test");
        this.company.addEmployee(new Employee("Test Empl.", Qualification.DRIVER, 1200));
    }

    @Test
    public void rename() {
        String expectedName = "Test company renamed";
        TransportCompany c = new TransportCompany("Test company");
        c.rename(expectedName);
        assertEquals(expectedName, c.getName());
    }

    @Test
    public void testToString() {
        TransportCompany company = new TransportCompany("Test Company");
        String expected = "<TransportCompany>(Test Company)>";
        String actual = company.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void getEmployees() {
        Employee emp = new Employee("Test Empl.", Qualification.DRIVER, 1200);
        this.company.addEmployee(emp);
        assertEquals(2, this.company.getEmployees().size());
    }

    @Test
    public void addEmployee() {
        Employee emp = new Employee("Test Empl.", Qualification.DRIVER, 1200);
        this.company.addEmployee(emp);
        assertTrue(this.company.getEmployees().contains(emp));
    }

    @Test
    public void removeEmployee() {
        Employee emp = new Employee("Test", Qualification.TRANSPORTER, 1200);
        this.company.addEmployee(emp);
        this.company.removeEmployee(emp);
        assertFalse(this.company.getEmployees().contains(emp));
    }

    @Test
    public void addClient() {
        Client client = new Client("Test client");
        this.company.addClient(client);
        assert this.company.getClients().contains(client);
    }

    @Test
    public void deleteClient() {
        Client client = new Client("Test client");
        this.company.addClient(client);
        this.company.deleteClient(client);
        assertFalse(this.company.getClients().contains(client));
    }

    @Test
    public void addVehicle() {
        Vehicle v = new Vehicle(VehicleType.CARGO);
        this.company.addVehicle(v, 10);
        assertNotNull(this.company.getVehicleOfType(VehicleType.CARGO));
    }

    @Test
    public void getVehiclesOfType() {
        Vehicle v = new Vehicle(VehicleType.CARGO);
        this.company.addVehicle(v, 10);
        assertEquals(10, this.company.getVehiclesOfType().get(VehicleType.CARGO));
    }

    @Test
    public void addRevenue() {
        this.company.addRevenue(100.0);
        assertEquals(100.0, this.company.getRevenue());
    }

    @Test
    public void addFare() {
        Fare fare = new Fare(new Client("Test"), "Sofia", "Varna");
        this.company.addFare(fare);
        assertTrue(this.company.getFares().contains(fare));
    }

    @Test
    public void deleteFare() {
        Fare fare = new Fare(new Client("Test"), "Sofia", "Varna");
        this.company.addFare(fare);
        this.company.deleteFare(fare);
        assertFalse(this.company.getFares().contains(fare));
    }
}
