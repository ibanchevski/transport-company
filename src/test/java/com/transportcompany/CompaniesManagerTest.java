package com.transportcompany;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CompaniesManagerTest {
    CompaniesManager companiesManager;

    @BeforeAll public void setUp() {
        this.companiesManager = new CompaniesManager();
        this.companiesManager.addCompany(new TransportCompany("Test Company 1"));
        this.companiesManager.addCompany(new TransportCompany("Test Company 2"));
        this.companiesManager.addCompany(new TransportCompany("Test Company 3"));
        this.companiesManager.selectCompany(2);
    }

    @Test
    public void createCompany() {
        TransportCompany company = new TransportCompany("Test company");
        this.companiesManager.addCompany(company);

        assertTrue(this.companiesManager.getCompanies().contains(company));
    }

    @Test
    public void renameCompany() {
        TransportCompany company2 = this.companiesManager.getCompanies().get(2);
        this.companiesManager.renameCompany("Test Company 2 - RENAMED");

        assertNotSame(company2.getName(), "Test Company 2");
    }

    @Test
    public void deleteCompany() {
        ArrayList<TransportCompany> originalCompanies;
        originalCompanies = new ArrayList<>(this.companiesManager.getCompanies());

        this.companiesManager.deleteCompany(3);

        assertTrue(this.companiesManager.getCompanies().size() < originalCompanies.size());
    }

    @Test
    public void selectCopmany() {
        TransportCompany expectedSelectedCompany = this.companiesManager.getCompanies().get(2);
        this.companiesManager.selectCompany(2);
        TransportCompany actualSelectedCompany = this.companiesManager.getCurrentCompany();

        assertSame(expectedSelectedCompany, actualSelectedCompany);
    }

    @Test
    public void addFare() {
        Fare fare = new Fare(new Client("Test"), "Sofia", "Varna");
        this.companiesManager.addFare(fare);
        assertTrue(this.companiesManager.getCurrentCompany().getFares().contains(fare));
    }
}
