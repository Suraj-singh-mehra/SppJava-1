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

    @Test
    public void covertToStorageRightAddress() throws Exception {
        AddNewStorageDto dto = new AddNewStorageDto();
        dto.setAddress("test test");
        dto.setCapacity(1234);

        Storage storage = DtoToStorageConverter.covertToStorage(dto);
        assertEquals("test test", storage.getAddress());
    }

    @Test
    public void covertToStorageInversion() throws Exception {
        AddNewStorageDto dto = new AddNewStorageDto();
        dto.setAddress("Baker Street");
        dto.setCapacity(1000);

        Storage storage = DtoToStorageConverter.covertToStorage(dto);

        assertNotEquals("BakerStreet", storage.getCapacity());
        assertNotEquals(1000, storage.getAddress());
    }

    @Test
    public void covertToStorageTestCompany() throws Exception {
        AddNewStorageDto dto = new AddNewStorageDto();
        dto.setAddress("test test");
        dto.setCapacity(1234);

        Storage storage = DtoToStorageConverter.covertToStorage(dto);
        assertEquals(null, storage.getCompany());
    }

    @Test
    public void covertToStorageFailed() throws Exception {
        AddNewStorageDto dto = new AddNewStorageDto();
        dto.setAddress("Baker Street");
        dto.setCapacity(1000);

        Storage storage = DtoToStorageConverter.covertToStorage(dto);

        assertNotEquals(0, storage.getCapacity());
        assertNotEquals("", storage.getAddress());
    }
}