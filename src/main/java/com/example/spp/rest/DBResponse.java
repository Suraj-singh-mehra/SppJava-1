package com.example.spp.rest;

import com.example.spp.models.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DBResponse {
    private List<Item> items;
    private int totalItems;
}
