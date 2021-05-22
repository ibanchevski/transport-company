package com.transportcompany.utils.controls;

import com.transportcompany.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Menu {
    private final BufferedReader reader;
    private final CompaniesManager cm;

    public Menu() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.cm = new CompaniesManager();
    }

    /**
     * Alias for System.out.print()
     * @param line  Line to print
     */
    private void print(String line) {
        System.out.print(line);
    }

    /**
     * Alias for System.out.println()
     * @param line Line to print
     */
    private void println(String line) {
        System.out.println(line);
    }

    /**
     * Prints view heading
     * @param heading The heading of the view
     */
    private void printHeading(String heading) {
        this.println("=========|| " + heading + " ||=========");
    }

    /**
     * Asks user for input with custom input prompt
     * @param prompt Input's prompt
     * @return String: User's input
     */
    private String input(String prompt) {
        this.print(prompt+" ");

        try {
            return this.reader.readLine();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return "";
        }
    }

    public void init() {
        this.createCompany();
    }

    public void createCompany() {
        this.printHeading("Create new company");
        String companyName = this.input("Company name:");
        this.cm.addCompany(new TransportCompany(companyName));
        this.companiesView();
    }

    public void companiesView() {
        this.cm.displayCompaniesInTable();
        this.println("Actions:");
        this.println("1. Select company");
        this.println("2. Add company");
        this.println("3. Delete company");
        this.println("4. Sort companies");
        this.println("0. Exit");

        String action = this.input("Select action:");

        switch (action) {
            case "0": System.exit(0); break;
            case "1":
                String selected = this.input("Select company(index):");
                this.cm.selectCompany(Integer.parseInt(selected) - 1);
                this.companyView();
                break;
            case "2":
                this.createCompany();
                break;
            case "3":
                String companyToDelete = this.input("Select company to delete(index):");
                try {
                    this.cm.deleteCompany(Integer.parseInt(companyToDelete) - 1);
                } catch (IndexOutOfBoundsException exception) {
                    this.println("No companies!");
                }
                this.companiesView();
                break;
            case "4":
                this.println("Sort options:");
                this.println("1. By name");
                this.println("2. By revenue");
                this.println("0. Back");
                String sortOption = this.input("Select option:");

                if (sortOption.equals("0")) {
                    this.companiesView();
                }

                if (sortOption.equals("1")) {
                    this.cm.sortByName();
                } else {
                    this.cm.sortByRevenue();
                }
                this.companiesView();
                break;
            default:
                companiesView();
        }
    }

    public void companyView() {
        this.printHeading("Company view");
        this.println("Name: " + this.cm.getCurrentCompany().getName());
        this.println("Revenue: " + this.cm.getCurrentCompany().getRevenue());
        this.println("Employees: " + this.cm.getCurrentCompany().getEmployees().size());
        this.println("Clients: " + this.cm.getCurrentCompany().getClients().size());
        this.println("Fares: " + this.cm.getCurrentCompany().getFares().size());
        this.println("+-------------------------------------+");

        this.println("Actions:");
        this.println("1. Rename");
        this.println("2. Add employee");
        this.println("3. Add client");
        this.println("4. Add vehicle");
        this.println("5. Add fare");
        this.println("6. List employees");
        this.println("7. List clients");
        this.println("8. List fares");
        this.println("0. Back");

        String action = this.input("Select action:");

        switch(action) {
            case "0": this.companiesView(); break;
            case "1": this.renameCompanyView(); break;
            case "2": this.addEmployeeView(); break;
            case "3": this.addClientView(); break;
            case "4": this.addVehicleView(); break;
            case "5": this.addFareView(); break;
            case "6": this.employeesView(); break;
            case "7": this.clientsView(); break;
            case "8": this.faresView(); break;
        }
    }

    public void renameCompanyView() {
        this.printHeading("Rename company");
        String newName = this.input("New name:");
        this.cm.renameCompany(newName);
        this.companyView();
    }

    public void addEmployeeView() {
        this.printHeading("Add employee");
        String name = input("Name:");
        Qualification qualification = this.selectEmployeeQualification();
        String salary = input("Salary:");

        Employee employee = new Employee(name, qualification, Double.parseDouble(salary));
        this.cm.addEmployee(employee);

        this.companyView();
    }

    public void employeesView() {
        this.printHeading("Employees view");
        this.cm.displayEmployeesTable();
        this.println("Actions:");
        this.println("1. Select");
        this.println("2. Sort");
        this.println("0. Back");
        String action = this.input("Select action:");

        switch(action) {
            case "0": this.companyView(); break;
            case "1":
                String selectedEmployee = this.input("Select employee(index):");
                this.employeeView(this.cm.getEmployees().get(Integer.parseInt(selectedEmployee) - 1));
                break;
            case "2":
                this.println("Sort options:");
                this.println("1. By qualification");
                this.println("2. By salary");
                String sortType = this.input("Select sort option:");

                if (sortType.equals("1")) {
                    this.cm.sortEmployeesByQualification();
                } else {
                    this.cm.sortEmployeesBySalary();
                }
                this.employeesView();
                break;
        }
    }

    public void employeeView(Employee employee) {
        this.printHeading("Employee: " + employee.getName());
        this.println("Name: " + employee.getName());
        this.println("Qualification: " + employee.getQualification());
        this.println("Salary: " + employee.getSalary());
        this.println("+-------------------------------------------+");

        this.println("Actions:");
        this.println("1. Rename");
        this.println("2. Change salary");
        this.println("3. Change qualification");
        this.println("4. Delete");
        this.println("0. Back");

        String action = this.input("Select action:");

        switch (action) {
            case "0": this.employeesView(); break;
            case "1":
                String newName = this.input("New name:");
                employee.rename(newName);
                this.employeesView();
                break;
            case "2":
                String newSalary = this.input("New salary:");
                employee.setSalary(Double.parseDouble(newSalary));
                this.employeeView(employee);
                break;
            case "3":
                Qualification qualification = this.selectEmployeeQualification();
                employee.setQualification(qualification);
                this.employeeView(employee);
                break;
            case "4":
                String answr = input("Are you sure? [Y/N]:");
                if (answr.equals("Y") || answr.equals("y")) {
                    this.cm.getCurrentCompany().removeEmployee(employee);
                    this.employeesView();
                    break;
                }
                this.employeeView(employee);
                break;
        }
    }

    private Qualification selectEmployeeQualification() {
        int index = 0;
        ArrayList<Qualification> qualifications = new ArrayList<>();
        for (Qualification q : Qualification.values()) {
            qualifications.add(q);
            this.println(++index + ". " + q);
        }
        int selectedIndex = Integer.parseInt(this.input("Qualification:"));

        if (selectedIndex < 1 || selectedIndex > Qualification.values().length) {
            this.println("Invalid selection!");
            return selectEmployeeQualification();
        }

        return qualifications.get(selectedIndex - 1);
    }

    public void addClientView() {
        this.printHeading("Add client");
        String name = this.input("Client name:");
        this.cm.getCurrentCompany().addClient(new Client(name));
        this.companyView();
    }

    public void clientsView() {
        this.cm.displayClientsTable();
        this.println("Actions:");
        this.println("1. Select");
        this.println("2. Delete");
        this.println("0. Back");
        String action = this.input("Select action:");

        switch (action) {
            case "0": this.companyView(); break;
            case "1":
                String clientIndex = this.input("Select client(index):");
                Client selectedClient = this.cm.getCurrentCompany().getClients().get(Integer.parseInt(clientIndex) - 1);
                this.clientView(selectedClient);
                break;
            case "2":
                String clientToDelete = this.input("Select client to delete(index):");
                this.cm.getCurrentCompany().getClients().remove(Integer.parseInt(clientToDelete) - 1);
                this.clientsView();
                break;
            default:
                this.println("Invalid option!");
                this.clientsView();
        }
    }

    public void clientView(Client client) {
        this.printHeading("Client: " + client.getName());
        this.println("Actions:");
        this.println("1. Rename");
        this.println("0. Back");
        String action = this.input("Select action:");

        switch (action) {
            case "0":
                this.clientsView();
                break;
            case "1":
                String newName = this.input("New name:");
                client.rename(newName);
                this.clientView(client);
                break;
            default:
                this.println("Invalid option!");
                this.clientView(client);
                break;
        }
    }

    public void addVehicleView() {
        this.printHeading("New vehicle");
        this.println("Vehicle types:");
        this.println("1. Transportation (> 12 people)");
        this.println("2. Cargo (heavy goods)");
        this.println("3. Combustible (flammable goods)");
        String selectedType = this.input("Select type:");
        String numberOfVehicles = this.input("Number of vehicles");
        VehicleType vehicleType;
        if (selectedType.equals("1")) {
            vehicleType = VehicleType.TRANSPORT;
        } else if (selectedType.equals("2")) {
            vehicleType = VehicleType.CARGO;
        } else {
            vehicleType = VehicleType.COMBUSTIBLE;
        }

        this.cm.getCurrentCompany().addVehicle(new Vehicle(vehicleType),  Integer.parseInt(numberOfVehicles));
        this.companyView();
    }

    public void addFareView() {
        this.printHeading("New fare");
        Fare fare;

        if (this.cm.getCurrentCompany().getClients().size() == 0) {
            this.println("Add clients first!");
            this.companyView();
            return;
        }

        if (this.cm.getCurrentCompany().getEmployees().size() == 0) {
            this.println("Add employees!");
            this.companyView();
            return;
        }

        // Select client
        Client client = this.selectFareClient();

        // Select starting/ending point
        String startPoint = this.input("Start location:");
        String endPoint = this.input("End location:");

        fare = new Fare(client, startPoint, endPoint);

        // Type of transported goods
        Fare.FareType fareType = this.selectFareType();
        fare.setGoodsType(fareType);

        // Select start/end date
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        this.print("Start date(dd-MM-yyy HH:mm):");
        LocalDateTime startDate = this.selectFareDate(dateTimeFormatter);

        this.print("End date(dd-MM-yyy HH:mm):");
        LocalDateTime endDate = this.selectFareDate(dateTimeFormatter);

        fare.setStartDate(startDate);
        fare.setEndDate(endDate);

        // Select driver
        this.setFareDriver(fare);

        // Set price
        fare.setPrice(Double.parseDouble(this.input("Set price:")));

        this.cm.addFare(fare);
        this.companyView();
    }

    /**
     * Display list of company's clients to select from
     * @return Client The selected client
     */
    private Client selectFareClient() {
        ArrayList<Client> clients = this.cm.getCurrentCompany().getClients();

        this.cm.displayClientsTable();

        int clientIndex = Integer.parseInt(this.input("Select client:"));
        return clients.get(clientIndex - 1);
    }

    /**
     * Displays the possible fare types for the user
     * to choose from.
     * @return FareType
     */
    private Fare.FareType selectFareType() {
        this.println("Select fare type");
        this.println("1. People transportation");
        this.println("2. Heavy goods");
        this.println("3. Flammable goods");
        String selectedFareType = this.input("Select type(index):");

        if (selectedFareType.equals("1")) {
            return Fare.FareType.PEOPLE;
        }
        if (selectedFareType.equals("2")) {
            return Fare.FareType.CARGO;
        }
        if (selectedFareType.equals("3")) {
            return Fare.FareType.FLAMMABLE;
        }
        this.println("Invalid selection!");
        return selectFareType();
    }

    /**
     * Asks the user for a date (format: dd-MM-yyy HH:mm:ss)
     * @param formatter The DateTimeFormatter
     * @return LocalDateTime
     */
    private LocalDateTime selectFareDate(DateTimeFormatter formatter) {
        String dateString = this.input("");
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException exception) {
            this.println("Invalid date format!");
            return selectFareDate(formatter);
        }

        return date;
    }

    /**
     * Sets driver for the given fare
     * @param fare The fare for which to set driver
     */
    private void setFareDriver(Fare fare) {
        // Show drivers
        this.cm.displayEmployeesTable();

        int driverIndex = Integer.parseInt(this.input("Select driver(index):")) - 1;

        if (driverIndex == -1) {
            this.companyView();
            return;
        }

        Employee selectedDriver = this.cm.getEmployees().get(driverIndex);

        try {
            fare.setDriver(selectedDriver);
        } catch (EmployeeException exception) {
            this.println(exception.getMessage());
            setFareDriver(fare);
        }

        selectedDriver.addFare(fare);
    }

    public void faresView() {
        this.printHeading("Fares");
        this.cm.displayFaresTable();
        this.println("Actions:");
        this.println("1. Complete");
        this.println("2. Delete");
        this.println("3. Sort by destination");
        this.println("0. Back");
        String action = this.input("Select action:");

        int fareIndex = Integer.parseInt(this.input("Select fare:")) - 1;

        switch (action) {
            case "0": this.companyView(); break;
            case "1":
                this.cm.completeFare(fareIndex);
                this.faresView();
                break;
            case "2":
                this.cm.deleteFare(fareIndex);
                this.faresView();
                break;
            case "3":
                this.cm.sortFaresByDestination();
                this.faresView();
                break;

        }
    }
}