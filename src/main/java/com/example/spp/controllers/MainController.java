package com.example.spp.controllers;

import com.example.spp.dto.AddNewItemDto;
import com.example.spp.models.DBResponse;
import com.example.spp.models.Item;
import com.example.spp.service.TestBDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {

    @Autowired
    private TestBDService testBDService;

    @RequestMapping(value = "/getAllDataFromItems", method = RequestMethod.GET)
    public List<Item> getItems(@RequestParam(name = "_page") int page, @RequestParam(name = "_limit") int limit) {
        DBResponse response = testBDService.getItems();
        return response.getItems();
    }

    @RequestMapping(value = "/addNewItem", method = RequestMethod.POST)
    public boolean addNewItem(@Validated @RequestBody AddNewItemDto newItemDto) {
        return testBDService.addNewItem(newItemDto);
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.PUT)
    public void updateItem(@Validated @RequestBody AddNewItemDto newItemDto) {
        testBDService.updateItem(newItemDto);
    }

    @RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable("id") Long id) {
        testBDService.deleteItem(id);
    }
}
