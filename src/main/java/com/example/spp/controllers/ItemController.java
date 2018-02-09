package com.example.spp.controllers;

import com.example.spp.dto.AddNewItemDto;
import com.example.spp.rest.DBResponse;
import com.example.spp.models.Item;
import com.example.spp.service.TestBDItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {

    @Autowired
    private TestBDItemsService testBDItemsService;

    @RequestMapping(value = "/getAllDataFromItems", method = RequestMethod.GET)
    public List<Item> getItems(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit) {
        DBResponse response = testBDItemsService.getItems();
        return response.getItems();
    }

    @RequestMapping(value = "/addNewItem", method = RequestMethod.POST)
    public boolean addNewItem(@Validated @RequestBody AddNewItemDto newItemDto) {
        return testBDItemsService.addNewItem(newItemDto);
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public void updateItem(@Validated @RequestBody AddNewItemDto newItemDto) {
        testBDItemsService.updateItem(newItemDto);
    }

    @RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable("id") Long id) {
        testBDItemsService.deleteItem(id);
    }
}
