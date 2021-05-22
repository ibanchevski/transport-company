package com.transportcompany;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientTest {
    Client client;

    @BeforeAll
    public void setUp() {
        this.client = new Client("Test Client");
    }

    @Test
    void getName() {
        assertEquals(this.client.getName(), "Test Client");
    }

    @Test
    void getTotalPayments() {
        Client client = new Client("Test");
        assertEquals(client.getTotalPayments(), 0.0);
    }

    @Test
    void hasPendingFares() {
        assert !new Client("Test").hasPendingFares();
    }

    @Test
    void setPendingOrder() {
        this.client.setPendingOrder();
        assert this.client.hasPendingFares();
    }

    @Test
    void rename() {
        String expectedName = "New Client Name";
        Client client = new Client("Old Name");
        client.rename(expectedName);
        assertEquals(client.getName(), expectedName);
    }

    @Test
    void addPayment() {
        this.client.addPayment(100.0);
        assert this.client.getTotalPayments() == 100.0;
    }
}