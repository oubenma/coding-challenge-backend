package com.example.codingchallengebackend.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="product")
public class Product {
    //I used lombok to generate getters and setters and constructors
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private float originalPrice;
    private float euroPrice;
    private String originalCurrency;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonIdentityReference(alwaysAsId = true)
    private Category category;

}
