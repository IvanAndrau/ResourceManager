package org.example.resourcemanagerwebtask.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("user") // Now uses the "role" column
public class RegularUser extends User {
    public RegularUser() {}

    public RegularUser(String username, String email, String password) {
        super(username, email, password);
    }
}