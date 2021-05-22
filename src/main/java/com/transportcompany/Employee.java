package com.transportcompany;

import java.util.ArrayList;

public class Employee implements Comparable<Employee> {
    private String name;
    private double salary;
    private Qualification qualification;
    private ArrayList<Fare> fares;
    private double faresIncome;

    public Employee(String name, Qualification qualification, double salary) {
        this.name = name;
        this.qualification = qualification;
        this.salary = salary;
        this.fares = new ArrayList<>();
        this.faresIncome = 0;
    }

    public String getName() { return this.name; }
    public double getSalary() { return this.salary; }
    public Qualification getQualification() { return this.qualification; }

    public void rename(String newName) { this.name = newName; }
    public void setSalary(double newSalary) { this.salary = newSalary;}
    public void setQualification(Qualification qualification) { this.qualification = qualification; }

    public void addFare(Fare fare) {
        this.fares.add(fare);
    }

    public ArrayList<Fare> getFares() {
        return this.fares;
    }

    public void addFareIncome(double income) {
        this.faresIncome += income;
    }

    public double getFaresIncome() {
        return this.faresIncome;
    }

    @Override
    public String toString() {
        return "<Employee>(" + this.name + ", " + " " + this.qualification + ")";
    }

    @Override
    public int compareTo(Employee employee) {
        return (int)this.salary - (int)employee.getSalary();
    }
}
