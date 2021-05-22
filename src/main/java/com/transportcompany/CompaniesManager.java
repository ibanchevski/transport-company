package com.transportcompany;
import com.transportcompany.utils.controls.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompaniesManager {
    private final ArrayList<TransportCompany> companies;
    private TransportCompany currentCompany;

    public CompaniesManager() {
        this.companies = new ArrayList<>();
        this.currentCompany = null;
    }

    public void addCompany(TransportCompany company) {
        this.companies.add(company);
    }

    public ArrayList<TransportCompany> getCompanies() {
        return this.companies;
    }

    public void renameCompany(String newName) {
        this.currentCompany.rename(newName);
    }

    public void deleteCompany(int companyIndex) {
        this.companies.remove(companyIndex);
    }

    public void selectCompany(int index) {
        this.currentCompany = this.companies.get(index);
    }

    public TransportCompany getCurrentCompany() {
        return this.currentCompany;
    }

    public ArrayList<Employee> getEmployees() {
        return this.currentCompany.getEmployees();
    }

    public void addEmployee(Employee employee) {
        this.currentCompany.addEmployee(employee);
    }

    public void addFare(Fare fare) {
        this.currentCompany.addFare(fare);
        fare.getClient().setPendingOrder();
    }

    public void completeFare(int fareIndex) {
        Fare fare = this.currentCompany.getFares().get(fareIndex);
        fare.complete();
        this.currentCompany.addRevenue(fare.getPrice());
        fare.getDriver().addFareIncome(fare.getPrice());
        fare.getClient().addPayment(fare.getPrice());
    }

    public void deleteFare(int fareIndex) {
        Fare fare = this.currentCompany.getFares().get(fareIndex);
        this.currentCompany.deleteFare(fare);
    }

    /**
     * Create companies table and display it.
     */
    public void displayCompaniesInTable() {
        Table table = new Table("Companies");
        table.addRow(new ArrayList<String>(){
            {
                add("#");
                add("Name");
                add("Employees");
                add("Clients");
                add("Revenue");
            }
        });

        this.companies.forEach(company -> {
            String index = "" + (this.companies.indexOf(company) + 1);
            table.addRow(new ArrayList<>() {
                {
                    add(index);
                    add(company.getName());
                    add(String.valueOf(company.getEmployees().size()));
                    add(String.valueOf(company.getClients().size()));
                    add(String.valueOf(company.getRevenue()));
                }
            });
        });
        table.display();
    }

    public void sortByName() {
        Collections.sort(this.companies);
    }

    public void sortByRevenue() {
        this.companies.sort(Comparator.comparingInt(transportCompany -> (int) transportCompany.getRevenue()));
    }

    public void sortEmployeesBySalary() {
        Collections.sort(this.currentCompany.getEmployees());
    }

    public void sortEmployeesByQualification() {
        this.currentCompany.getEmployees().sort(Comparator.comparing(Employee::getQualification));
    }

    public void sortFaresByDestination() {
        this.currentCompany.getFares().sort(Comparator.comparing(Fare::getEndDestination));
    }

    /**
     * Creates table of the company's employees
     * and displays it.
     */
    public void displayEmployeesTable() {
        Table table = new Table("Employees");
        table.addRow(new ArrayList<String>(){
            {
                add("#");
                add("Name");
                add("Qualification");
                add("Salary");
                add("Generated revenue");
                add("Fares");
            }
        });
        ArrayList<Employee> employees = this.currentCompany.getEmployees();

        employees.forEach(employee -> {
            String index = "" + (employees.indexOf(employee) + 1);
            table.addRow(new ArrayList<>() {
                {
                    add(index);
                    add(employee.getName());
                    add(String.valueOf(employee.getQualification()));
                    add(String.valueOf(employee.getSalary()));
                    add(String.valueOf(employee.getFaresIncome()));
                    add(String.valueOf(employee.getFares().size()));
                }
            });
        });

        table.display();
    }

    /**
     * Creates table for the company's clients
     * and displays it.
     */
    public void displayClientsTable() {
        Table table = new Table("Clients");
        table.addRow(new ArrayList<String>(){
            {
                add("#");
                add("Name");
                add("Total payments");
                add("Has Pending fares");
            }
        });
        ArrayList<Client> clients = this.currentCompany.getClients();

        for (Client client : clients) {
            String index = "" + (clients.indexOf(client) + 1);
            table.addRow(new ArrayList<String>() {
                {
                    add(index);
                    add(client.getName());
                    add(String.valueOf(client.getTotalPayments()));
                    add(String.valueOf(client.hasPendingFares()));
                }
            });
        }

        table.display();
    }

    /**
     * Creates table showing the company's fares
     * and displays it.
     */
    public void displayFaresTable() {
        Table table = new Table("Fares");
        table.addRow(new ArrayList<String>(){
            {
                add("#");
                add("Start date");
                add("End date");
                add("Start location");
                add("End location");
                add("Driver");
                add("Price");
                add("Status");
            }
        });
        ArrayList<Fare> fares = this.currentCompany.getFares();

        for (Fare fare : fares) {
            String index = "" + (fares.indexOf(fare) + 1);
            table.addRow(new ArrayList<String>() {
                {
                    add(index);
                    add(fare.getStartDate().toString());
                    add(fare.getEndDate().toString());
                    add(fare.getStartDestination());
                    add(fare.getEndDestination());
                    add(fare.getDriver().getName());
                    add(String.valueOf(fare.getPrice()));
                    add(fare.getStatus());
                }
            });
        }

        table.display();
    }
}
