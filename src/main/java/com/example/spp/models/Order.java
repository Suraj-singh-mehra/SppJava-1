package com.example.spp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User driver;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @NotNull
    @Column
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull
    @Column
    private int amount;
}
