package com.transportcompany;

import java.util.ArrayList;
import java.util.HashMap;

public class TransportCompany implements Comparable<TransportCompany> {
    private String name;
    private double revenue = 0.0;
    private ArrayList<Employee> employees;
    private ArrayList<Client> clients;
    private ArrayList<Vehicle> vehicles;
    private HashMap<VehicleType, Integer> vehiclesOfType;
    private ArrayList<Fare> fares;

    public TransportCompany(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.vehicles = new ArrayList<>();
        this.vehiclesOfType = new HashMap<>();
        this.fares = new ArrayList<>();
    }

    public String getName() { return this.name; }
    public double getRevenue() { return this.revenue; }
    public ArrayList<Employee> getEmployees() { return this.employees; }
    public HashMap<VehicleType, Integer> getVehiclesOfType() { return this.vehiclesOfType; }

    public void addClient(Client client) {
        this.clients.add(client);
    }
    public ArrayList<Client> getClients() { return this.clients; }
    public void deleteClient(Client client) {
        this.clients.remove(client);
    }

    public void addEmployee(Employee employee) { this.employees.add(employee); }
    public void removeEmployee(Employee employee) { this.employees.remove(employee); }

    public void addVehicle(Vehicle vehicle, int numberOfVehicles) {
        VehicleType type = vehicle.getType();
        int vehiclesOfType = 0;
        try {
            vehiclesOfType = this.vehiclesOfType.get(type);
            vehiclesOfType += numberOfVehicles;
        } catch (NullPointerException e) {
            vehiclesOfType = numberOfVehicles;
        }

        this.vehicles.add(vehicle);
        this.vehiclesOfType.put(type, vehiclesOfType);
    }

    public Vehicle getVehicleOfType(VehicleType type) {
        for (Vehicle v : this.vehicles) {
            if (v.getType() == type) {
                return v;
            }
        }
        return null;
    }

    public void addFare(Fare fare) { this.fares.add(fare); }
    public ArrayList<Fare> getFares() { return this.fares; }
    public void deleteFare (Fare fare) { this.fares.remove(fare); }

    public void rename(String newName) { this.name = newName; }

    public void addRevenue(double revenue) {
        this.revenue += revenue;
    }

    @Override
    public String toString() {
        return "<TransportCompany>(" + this.name + ")>";
    }

    @Override
    public int compareTo(TransportCompany company) {
        return this.name.compareTo(company.getName());
    }
}
