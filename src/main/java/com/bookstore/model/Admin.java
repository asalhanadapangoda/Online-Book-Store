package com.bookstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Admin extends User {
    // Admins only have the base user fields for now as per requirements
    
    public Admin(String id, String username, String email, String password) {
        super(id, username, email, password, "ADMIN");
    }
}
