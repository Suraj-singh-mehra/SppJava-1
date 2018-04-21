package com.example.spp.converter;

import com.example.spp.dto.AddNewStorageDto;
import com.example.spp.models.Storage;
import org.junit.Test;

import static org.junit.Assert.*;

public class DtoToStorageConverterTest {
    @Test
    public void covertToStorage() throws Exception {
        AddNewStorageDto dto = new AddNewStorageDto();
        dto.setAddress("Baker Street");
        dto.setCapacity(1000);

        Storage storage = DtoToStorageConverter.covertToStorage(dto);

        assertEquals(1000, storage.getCapacity());
        assertEquals("Baker Street", storage.getAddress());
    }

}