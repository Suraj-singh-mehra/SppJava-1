package com.example.spp.service;

import com.example.spp.dto.AddNewItemDto;
import com.example.spp.models.Item;
import com.example.spp.repository.ItemRepository;
import com.example.spp.rest.DBResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class DBItemsServiceTest extends BaseServiceTest {

    @MockBean
    private ItemRepository itemRepository;
    @Autowired
    private TestBDItemsService itemsService;

    @Before
    public void setupMock() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("apple");
        item1.setCategory("mobile phones");
        item1.setPrice(50);
        Item item2 = new Item();
        item2.setName("water");
        item2.setCategory("groceries");
        item2.setPrice(500);
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        when(itemRepository.save(any(Item.class))).thenReturn(item1);
        when(itemRepository.findOne(any(Long.class))).thenReturn(item1);
        when(itemRepository.findAll()).thenReturn(items);
    }

    @Test
    public void getItems() throws Exception {
        DBResponse response = itemsService.getItems();
    }

    @Test
    public void addNewItem() throws Exception {
        AddNewItemDto item = new AddNewItemDto();
        item.setId(1);
        item.setPrice(150);

        assertEquals(false, itemsService.addNewItem(item));
    }

    @Test
    public void updateItem() throws Exception {
        AddNewItemDto item = new AddNewItemDto();
        item.setPrice(150);
        itemsService.updateItem(item);
    }

    @Test
    public void deleteItem() throws Exception {
        itemsService.deleteItem(1L);
    }

}