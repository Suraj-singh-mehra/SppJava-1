package com.example.spp.converter;

import com.example.spp.dto.AddNewItemDto;
import com.example.spp.models.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class DtoToItemConverterTest {
    @Test
    public void covertToItem() throws Exception {
        AddNewItemDto dto = new AddNewItemDto();
        dto.setCategory("diary");
        dto.setName("milk");
        dto.setPrice(100);

        Item item = DtoToItemConverter.covertToItem(dto);

        assertEquals("diary", item.getCategory());
        assertEquals("milk", item.getName());
        assertEquals(100, item.getPrice());
    }

    @Test
    public void covertToItemInversion() throws Exception {
        AddNewItemDto dto = new AddNewItemDto();
        dto.setCategory("diary");
        dto.setName("milk");
        dto.setPrice(100);

        Item item = DtoToItemConverter.covertToItem(dto);

        assertNotEquals("milk", item.getCategory());
        assertNotEquals("diary", item.getName());
    }

    @Test
    public void covertToItemFailed() throws Exception {
        AddNewItemDto dto = new AddNewItemDto();
        dto.setCategory("diary");
        dto.setName("milk");
        dto.setPrice(100);

        Item item = DtoToItemConverter.covertToItem(dto);

        assertNotEquals("", item.getCategory());
        assertNotEquals("", item.getName());
        assertNotEquals(0, item.getPrice());
    }

    @Test
    public void covertToItemRightPrice() throws Exception {
        AddNewItemDto dto = new AddNewItemDto();
        dto.setCategory("diary");
        dto.setName("milk");
        dto.setPrice(100);

        Item item = DtoToItemConverter.covertToItem(dto);

        assertNotEquals(10, item.getPrice());
    }

    @Test
    public void covertToItemRightName() throws Exception {
        AddNewItemDto dto = new AddNewItemDto();
        dto.setCategory("diary");
        dto.setName("milk");
        dto.setPrice(100);

        Item item = DtoToItemConverter.covertToItem(dto);

        assertEquals("milk", item.getName());
    }

    @Test
    public void covertToItemRightCategory() throws Exception {
        AddNewItemDto dto = new AddNewItemDto();
        dto.setCategory("diary");

        Item item = DtoToItemConverter.covertToItem(dto);

        assertEquals("diary", item.getCategory());
    }
}