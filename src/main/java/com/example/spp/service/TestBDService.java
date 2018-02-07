package com.example.spp.service;

import com.example.spp.converter.DtoToItemConverter;
import com.example.spp.dto.AddNewItemDto;
import com.example.spp.models.DBResponse;
import com.example.spp.models.Item;
import com.example.spp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class TestBDService {

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
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            Date date = new java.sql.Date(format.parse(newItemDto.getExpiryDate()).getTime());
            item.setExpireDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }
}
