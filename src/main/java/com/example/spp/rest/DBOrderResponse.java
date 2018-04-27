package com.example.spp.rest;

import com.example.spp.models.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DBOrderResponse {
    private List<Order> orders;
    private int totalOrders;
}
