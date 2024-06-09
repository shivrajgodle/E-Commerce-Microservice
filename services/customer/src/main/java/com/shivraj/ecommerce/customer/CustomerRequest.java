package com.shivraj.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "Customer first name is required")
         String firstName,
         @NotNull(message = "Customer last name is required")
         String lastName,
         @Email(message = "Customer email is required")
         String email,
         Address address
         ) {
}
