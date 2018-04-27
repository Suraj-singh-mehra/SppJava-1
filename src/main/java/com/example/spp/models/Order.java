package com.example.spp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn( name = "user_id" )
    private User user;

    @ManyToOne
    @JoinColumn
    private Storage storage;

    @NotNull
    @Column
    private int totalPrice;

    @ManyToOne
    @JoinColumn
    private Item item;

    @NotNull
    @Column
    private int amount;

    @NotNull
    @Column
    private String date;

    @NotNull
    @Column
    private boolean wasThere;
}
