package com.example.spp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String email;
}
