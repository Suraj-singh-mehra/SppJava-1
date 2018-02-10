package com.example.spp.service;

import com.example.spp.converter.DtoToItemConverter;
import com.example.spp.dto.AddNewItemDto;
import com.example.spp.rest.DBResponse;
import com.example.spp.models.Item;
import com.example.spp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestBDItemsService {

    @Autowired
    private ItemRepository itemRepository;

    public DBResponse getItems() {
        DBResponse response = new DBResponse();
        List<Item> items = itemRepository.findAll();
        response.setItems(items);
        response.setTotalItems(items.size());
        return response;
    }

    public boolean addNewItem(AddNewItemDto newItemDto) {
        boolean result = true;
        Item savedItem = itemRepository.save(DtoToItemConverter.covertToItem(newItemDto));
        if (savedItem != null) {
            result = false;
        }
        return result;
    }

    public void updateItem(AddNewItemDto newItemDto) {
        Item item = itemRepository.findOne(newItemDto.getId());
        item.setPrice(newItemDto.getPrice());
        item.setName(newItemDto.getName());
        item.setCategory(newItemDto.getCategory());
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }
}
