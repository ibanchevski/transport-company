package com.transportcompany;

public class Client {
    private String name;
    private boolean hasPendingFares = false;
    private double totalPayments = 0.0;

    public Client(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    public double getTotalPayments() { return totalPayments; }

    public boolean hasPendingFares() { return hasPendingFares; }

    public void rename(String newName) {
        this.name = newName;
    }

    public void addPayment(double amount) {
        this.totalPayments += amount;
    }

    public void setPendingOrder() {
        this.hasPendingFares = true;
    }
    @Override
    public String toString() {
        return "<Client(\"" + this.name + "\")>";
    }

}
