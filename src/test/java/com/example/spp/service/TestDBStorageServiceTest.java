package com.example.spp.service;

import com.example.spp.dto.AddNewStorageDto;
import com.example.spp.models.Company;
import com.example.spp.models.Storage;
import com.example.spp.repository.StorageRepository;
import com.example.spp.rest.DBStorageResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import java.util.List;

public class TestDBStorageServiceTest extends BaseServiceTest{
    @Autowired
    TestDBStorageService storageService;
    @MockBean
    private StorageRepository storageRepository;

//    private Company comp = new Company();

    @Before
    public void setupMock() {
        Company comp = new Company();
        comp.setName("testCompany");

        Storage storage1 = new Storage();
        storage1.setAddress("bla-bla");
        storage1.setCapacity(150);
        storage1.setCompany(comp);
        Storage storage2 = new Storage();
        storage2.setAddress("hi-hi");
        storage1.setCapacity(100);
        storage1.setCompany(comp);

        List<Storage> storagies = new ArrayList<>();
        storagies.add(storage1);
        storagies.add(storage2);

        Storage newStorage = new Storage();
        newStorage.setAddress("hi-hi-hi");
        newStorage.setCapacity(100);

        when(storageRepository.save(storage1)).thenReturn(storage1);
        when(storageRepository.save(storage2)).thenReturn(null);
        when(storageRepository.save(newStorage)).thenReturn(newStorage);
        when(storageRepository.findOne(5L)).thenReturn(null);
        when(storageRepository.findOne(1L)).thenReturn(storage1);
        when(storageRepository.findAll()).thenReturn(storagies);
    }

    @Test
    public void getStorages() throws Exception {
        DBStorageResponse response = storageService.getStorages();
        assertEquals(2, response.getTotalItems());
    }

    @Test
    public void getStoragesActualData() throws Exception {
        DBStorageResponse response = storageService.getStorages();

        Company comp = new Company();
        comp.setName("testCompany");
        Storage storage1 = new Storage();
        storage1.setAddress("bla-bla");
        storage1.setCapacity(150);
        storage1.setCompany(comp);
        Storage storage2 = new Storage();
        storage2.setAddress("hi-hi");
        storage1.setCapacity(100);
        storage1.setCompany(comp);

        List<Storage> storagies = new ArrayList<>();
        storagies.add(storage1);
        storagies.add(storage2);

        assertEquals(storagies, response.getItems());
    }

    @Test
    public void getStoragesFailed() throws Exception {
        DBStorageResponse response = storageService.getStorages();
        assertNotEquals(0, response.getTotalItems());
    }

    @Test
    public void addNewStorageFailed() throws Exception {
        AddNewStorageDto newStorage = new AddNewStorageDto();
        newStorage.setAddress("hi-hi");
        newStorage.setCapacity(100);

        assertTrue(storageService.addNewStorage(newStorage));
    }

    @Test
    public void addNewStorage() throws Exception {
        AddNewStorageDto newStorage = new AddNewStorageDto();
        newStorage.setAddress("hi-hi-hi");
        newStorage.setCapacity(100);

        assertFalse(storageService.addNewStorage(newStorage));
    }

    @Test
    public void addNewStorageWithEmptyContent() throws Exception {
        AddNewStorageDto newStorage = new AddNewStorageDto();
        newStorage.setAddress("");
        newStorage.setCapacity(0);

        assertTrue(storageService.addNewStorage(newStorage));
    }

    @Test
    public void updateStorage() throws Exception {
        AddNewStorageDto newStorage = new AddNewStorageDto();
        newStorage.setId(1L);
        newStorage.setAddress("hi-hi");
        newStorage.setCapacity(100);

        storageService.updateStorage(newStorage);
    }

    @Test
    public void deleteStorage() throws Exception {
        storageService.deleteStorage(5L);
    }

}