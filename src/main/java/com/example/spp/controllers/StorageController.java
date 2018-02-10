package com.example.spp.controllers;

import com.example.spp.dto.AddNewItemDto;
import com.example.spp.dto.AddNewStorageDto;
import com.example.spp.models.Item;
import com.example.spp.models.Storage;
import com.example.spp.rest.DBResponse;
import com.example.spp.rest.DBStorageResponse;
import com.example.spp.service.TestBDItemsService;
import com.example.spp.service.TestDBStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StorageController {
    @Autowired
    private TestDBStorageService testDBStorageService;

    @RequestMapping(value = "/getAllDataFromStorage", method = RequestMethod.GET)
    public List<Storage> getStorage(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit) {
        DBStorageResponse response = testDBStorageService.getStorages();
        return response.getItems();
    }

    @RequestMapping(value = "/addNewStorage", method = RequestMethod.POST)
    public boolean addNewStorage(@Validated @RequestBody AddNewStorageDto newStorageDto) {
        return testDBStorageService.addNewStorage(newStorageDto);
    }

    @RequestMapping(value = "/updateStorage", method = RequestMethod.PUT)
    public void updateStorage(@Validated @RequestBody AddNewStorageDto newStorageDto) {
        testDBStorageService.updateStorage(newStorageDto);
    }

    @RequestMapping(value = "/deleteStorage/{id}", method = RequestMethod.DELETE)
    public void deleteStorage(@PathVariable("id") Long id) {
        testDBStorageService.deleteStorage(id);
    }
}
