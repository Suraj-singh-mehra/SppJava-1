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

        when(itemRepository.save(item1)).thenReturn(null);
        when(itemRepository.save(item2)).thenReturn(item1);
        when(itemRepository.findOne(5L)).thenReturn(null);
        when(itemRepository.findOne(1L)).thenReturn(item1);
        when(itemRepository.findAll()).thenReturn(items);
    }

    @Test
    public void getItems() throws Exception {
        DBResponse response = itemsService.getItems();
    }

    @Test
    public void getItemsRightCount() throws Exception {
        DBResponse response = itemsService.getItems();

        assertNotEquals(0, response.getTotalItems());
    }

    @Test
    public void getItemsActualData() throws Exception {
        DBResponse response = itemsService.getItems();

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

        assertEquals(items, response.getItems());
    }

    @Test
    public void getItemsTotalItems() throws Exception {
        DBResponse response = itemsService.getItems();

        assertEquals(2, response.getTotalItems());
    }

    @Test
    public void addNewItem() throws Exception {
        AddNewItemDto item2 = new AddNewItemDto();
        item2.setName("water");
        item2.setCategory("groceries");
        item2.setPrice(500);
        assertEquals(false, itemsService.addNewItem(item2));
    }

    @Test
    public void addNewItemWithEmptyContent() throws Exception {
        AddNewItemDto item2 = new AddNewItemDto();
        item2.setName("");
        item2.setCategory("");
        item2.setPrice(0);
        assertEquals(true, itemsService.addNewItem(item2));
    }

    @Test
    public void addNewItemFailed() throws Exception {
        AddNewItemDto item1 = new AddNewItemDto();

        item1.setId(1);
        item1.setName("apple");
        item1.setCategory("mobile phones");
        item1.setPrice(50);
        assertEquals(true, itemsService.addNewItem(item1));
    }

    @Test
    public void updateItem() throws Exception {
        AddNewItemDto item = new AddNewItemDto();
        item.setId(1L);
        item.setPrice(150);
        itemsService.updateItem(item);
    }

    @Test(expected = NullPointerException.class)
    public void updateItemFailed() throws Exception {
        AddNewItemDto item = new AddNewItemDto();
        item.setId(5L);
        item.setPrice(150);
        itemsService.updateItem(item);
    }

    @Test
    public void deleteItem() throws Exception {
        itemsService.deleteItem(1L);
    }

}