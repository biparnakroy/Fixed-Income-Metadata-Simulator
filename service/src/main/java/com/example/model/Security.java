
package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Security {
    @Id
    private String id;
    private String issuer;
    private String maturity;
    private double coupon;
}
