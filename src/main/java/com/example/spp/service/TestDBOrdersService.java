package com.example.spp.service;

import com.example.spp.converter.DtoToOrderConverter;
import com.example.spp.dto.AddNewOrderDto;
import com.example.spp.models.Order;
import com.example.spp.repository.OrderRepository;
import com.example.spp.repository.StorageRepository;
import com.example.spp.repository.UserRepository;
import com.example.spp.rest.DBOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestDBOrdersService {
    @Autowired
    private OrderRepository orderRepository;


    public DBOrderResponse getOrders() {
        DBOrderResponse response = new DBOrderResponse();
        List<Order> orders = orderRepository.findAll();
        response.setOrders(orders);
        response.setTotalOrders(orders.size());
        return response;
    }

    public boolean addNewOrder(AddNewOrderDto newOrderDto) {
        boolean result = true;
        Order savedItem = orderRepository.save(new DtoToOrderConverter().covertToOrder(newOrderDto));
        if (savedItem != null) {
            result = false;
        }
        return result;
    }

    public void updateOrder(AddNewOrderDto newOrderDto) {
        Order order = orderRepository.findOne(newOrderDto.getId());
        order.setDate(newOrderDto.getDate());
        order.setWasThere(newOrderDto.isWasThere());
        order.setTotalPrice(newOrderDto.getTotalPrice());
        order.setAmount(newOrderDto.getAmount());
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.delete(id);
    }
}
