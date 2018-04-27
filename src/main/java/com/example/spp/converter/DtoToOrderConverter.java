package com.example.spp.converter;


import com.example.spp.dto.AddNewOrderDto;
import com.example.spp.models.Order;

public class DtoToOrderConverter {

    public Order covertToOrder(AddNewOrderDto newOrderDto) {
        Order result = new Order();
        result.setAmount(newOrderDto.getAmount());
        result.setTotalPrice(newOrderDto.getTotalPrice());
        result.setId(newOrderDto.getId());
        result.setDate(newOrderDto.getDate());
        result.setWasThere(newOrderDto.isWasThere());
        return result;
    }
}
