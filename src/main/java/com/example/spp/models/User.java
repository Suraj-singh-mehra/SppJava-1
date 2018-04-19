package com.example.spp.models;

import com.example.spp.models.enums.UserRole;
import com.example.spp.models.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String fullname;

    @Column
    private String password;

    @Column
    private String biography;

    @Column
    private String imageUrl;

    @Column
    private String location;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @NotNull
    @Column
    private int salary;
}
