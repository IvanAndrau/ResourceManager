package org.example.resourcemanagerwebtask.Models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin() {}

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }
}
