package com.example.spp.controllers;

import com.example.spp.dto.AddNewStorageDto;
import com.example.spp.models.Storage;
import com.example.spp.repository.StorageRepository;
import com.example.spp.service.TestDBStorageService;
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
public class StorageControllerTest extends BaseControllerTest {

    @MockBean
    StorageRepository storageRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestDBStorageService testDBStorageService;

    @Before
    public void setUpMock() {
        Storage storage1 = new Storage();
        storage1.setCapacity(4);
        storage1.setAddress("Beruta");
        storage1.setId(1L);
        Storage storage2 = new Storage();
        storage2.setAddress("Kulman");
        List<Storage> storageList = new ArrayList<>();
        storageList.add(storage1);
        storageList.add(storage2);
        when(storageRepository.findAll()).thenReturn(storageList);
        when(storageRepository.save(any(Storage.class))).thenReturn(null);
        when(storageRepository.findOne(any(Long.class))).thenReturn(storage1);
    }

    @Test
    public void getStorage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/getAllDataFromStorage")
                .param("_page", "1")
                .param("_limit", "10")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", Matchers.equalTo("Beruta")))
                .andExpect(jsonPath("$[1].address", Matchers.equalTo("Kulman")));

    }

    @Test
    public void getStorageBadRequest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = get("/getAllDataFromStorage")
                .param("_page", "test")
                .param("_limit", "10")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest());

    }

    @Test
    public void addNewStorage() throws Exception {
        AddNewStorageDto addNewStorageDto = new AddNewStorageDto();
        addNewStorageDto.setAddress("grodo");
        addNewStorageDto.setCapacity(1000);
        addNewStorageDto.setId(1);

        String json = mapper.writeValueAsString(addNewStorageDto);
        MockHttpServletRequestBuilder requestBuilder = post("/addNewStorage")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.equalTo(true)));

    }

    @Test
    public void addNewStorageEmptyData() throws Exception {
        AddNewStorageDto addNewStorageDto = new AddNewStorageDto();
        addNewStorageDto.setAddress("");
        addNewStorageDto.setCapacity(0);
        addNewStorageDto.setId(1);

        String json = mapper.writeValueAsString(addNewStorageDto);
        MockHttpServletRequestBuilder requestBuilder = post("/addNewStorage")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.equalTo(true)));

    }

    @Test
    public void updateStorage() throws Exception {
        AddNewStorageDto addNewStorageDto = new AddNewStorageDto();
        addNewStorageDto.setCapacity(4);
        addNewStorageDto.setAddress("minsk");
        addNewStorageDto.setId(1);

        String json = mapper.writeValueAsString(addNewStorageDto);
        MockHttpServletRequestBuilder requestBuilder = put("/updateStorage")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    public void updateStorageWrongPath() throws Exception {
        AddNewStorageDto addNewStorageDto = new AddNewStorageDto();
        addNewStorageDto.setCapacity(4);
        addNewStorageDto.setAddress("minsk");
        addNewStorageDto.setId(1);

        String json = mapper.writeValueAsString(addNewStorageDto);
        MockHttpServletRequestBuilder requestBuilder = put("/update_storage")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteStorage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/deleteStorage/1")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    public void deleteStorageWrongPath() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/delete_storage/1")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isUnauthorized());

    }
}
