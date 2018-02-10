package com.example.spp.rest;

import com.example.spp.models.Storage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DBStorageResponse {
    private List<Storage> items;
    private int totalItems;
}
