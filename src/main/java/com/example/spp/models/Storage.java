package com.example.spp.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "storage")
@NoArgsConstructor
@AllArgsConstructor
public class Storage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
