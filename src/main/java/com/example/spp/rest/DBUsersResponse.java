package com.example.spp.rest;

import com.example.spp.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DBUsersResponse {
    private List<User> items;
    private int totalItems;
}
