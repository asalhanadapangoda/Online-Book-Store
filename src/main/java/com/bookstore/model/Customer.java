package com.bookstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Customer extends User {
    private String fullName;
    private String address;
    private String phoneNumber;

    public Customer(String id, String username, String email, String password, String fullName, String address, String phoneNumber) {
        super(id, username, email, password, "CUSTOMER");
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
