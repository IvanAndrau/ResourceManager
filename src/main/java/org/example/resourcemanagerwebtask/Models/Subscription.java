package org.example.resourcemanagerwebtask.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Subscription extends Resource {

    private double cost;
    private String renewalDate;
    private boolean autoRenew;

    public Subscription() {
    }

    public Subscription(String title, String username, String password, String email,
                        double cost, String renewalDate, boolean autoRenew) {
        super(title, username, password, email);
        this.cost = cost;
        this.renewalDate = renewalDate;
        this.autoRenew = autoRenew;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(String renewalDate) {
        this.renewalDate = renewalDate;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + getId() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cost=" + cost +
                ", renewalDate='" + renewalDate + '\'' +
                ", autoRenew=" + autoRenew +
                '}';
    }
}
