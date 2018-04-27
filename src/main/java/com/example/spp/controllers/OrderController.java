package com.example.spp.controllers;


import com.example.spp.dto.AddNewItemDto;
import com.example.spp.dto.AddNewOrderDto;
import com.example.spp.models.Order;
import com.example.spp.rest.DBOrderResponse;
import com.example.spp.service.TestDBOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private TestDBOrdersService testDBOrdersService;

    @RequestMapping(value = "/getAllDataFromOrders", method = RequestMethod.GET)
    public List<Order> getOrders(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit) {
        DBOrderResponse response = testDBOrdersService.getOrders();
        return response.getOrders();
    }

    @RequestMapping(value = "/addNewOrder", method = RequestMethod.POST)
    public boolean addNewOrder(@Validated @RequestBody AddNewOrderDto newOrderDto) {
        return testDBOrdersService.addNewOrder(newOrderDto);
    }

    @RequestMapping(value = "/updateOrder", method = RequestMethod.PUT)
    public void updateOrder(@Validated @RequestBody AddNewOrderDto newOrderDto) {
        testDBOrdersService.updateOrder(newOrderDto);
    }

    @RequestMapping(value = "/deleteOrder/{id}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("id") Long id) {
        testDBOrdersService.deleteOrder(id);
    }

}
