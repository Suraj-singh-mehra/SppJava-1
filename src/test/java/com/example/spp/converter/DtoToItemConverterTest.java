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

}