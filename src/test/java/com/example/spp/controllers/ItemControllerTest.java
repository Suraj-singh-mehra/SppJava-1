package com.example.spp.controllers;

import com.example.spp.dto.AddNewItemDto;
import com.example.spp.dto.AddNewStorageDto;
import com.example.spp.models.Item;
import com.example.spp.repository.ItemRepository;
import com.example.spp.service.TestBDItemsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "admin", authorities = "ADMINISTRATOR")
public class ItemControllerTest extends BaseControllerTest{

    @MockBean
    ItemRepository itemRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestBDItemsService testBDItemsService;

    @Before
    public void setUpMock() {
        Item item1 = new Item();
        item1.setName("first item");
        item1.setCategory("fruit");
        item1.setPrice(100);
        item1.setId(1L);
        Item item2 = new Item();
        item2.setName("second item");
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        when(itemRepository.findAll())
                .thenReturn(items);
        when(itemRepository.save(any(Item.class))).thenReturn(null);
        when(itemRepository.findOne(any(Long.class))).thenReturn(item1);
    }
    @Test
    public void getItems() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get("/getAllDataFromItems")
                .param("_page", "1")
                .param("_limit", "10")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("first item")))
                .andExpect(jsonPath("$[1].name", Matchers.equalTo("second item")));
    }

    @Test
    public void getItemsBadRequest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get("/getAllDataFromItems")
                .param("_page", "test")
                .param("_limit", "10")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addNewItem() throws Exception {
        AddNewItemDto addNewItemDto = new AddNewItemDto();
        addNewItemDto.setName("savychkin");
        addNewItemDto.setCategory("milk");
        addNewItemDto.setPrice(1000);
        addNewItemDto.setId(1);

        String json = mapper.writeValueAsString(addNewItemDto);
        MockHttpServletRequestBuilder requestBuilder = post("/addNewItem")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.equalTo(true)));
    }

    @Test
    public void addNewItemWrongContent() throws Exception {
        AddNewStorageDto addNewStorageDto = new AddNewStorageDto();
        addNewStorageDto.setAddress("grodo");
        addNewStorageDto.setCapacity(1000);
        addNewStorageDto.setId(1);


        String json = mapper.writeValueAsString(addNewStorageDto);
        MockHttpServletRequestBuilder requestBuilder = post("/addNewItem")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateItem() throws Exception {
        AddNewItemDto addNewItemDto = new AddNewItemDto();
        addNewItemDto.setName("item");
        addNewItemDto.setPrice(102);
        addNewItemDto.setCategory("chips");
        addNewItemDto.setId(1);

        String json = mapper.writeValueAsString(addNewItemDto);
        MockHttpServletRequestBuilder requestBuilder = put("/updateItem")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteItem() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/deleteItem/1")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void deleteItemWrongPath() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/delete_item/1")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }

}