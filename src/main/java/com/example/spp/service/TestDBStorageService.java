package com.example.spp.service;

import com.example.spp.converter.DtoToStorageConverter;
import com.example.spp.dto.AddNewItemDto;
import com.example.spp.dto.AddNewStorageDto;
import com.example.spp.models.Storage;
import com.example.spp.repository.StorageRepository;
import com.example.spp.rest.DBStorageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestDBStorageService {
    @Autowired
    private StorageRepository storageRepository;

    public DBStorageResponse getStorages() {
        DBStorageResponse response = new DBStorageResponse();
        List<Storage> storages = storageRepository.findAll();
        response.setItems(storages);
        response.setTotalItems(storages.size());
        return response;
    }

    public boolean addNewStorage(AddNewStorageDto newStorageDto) {
        boolean result = true;
        Storage savedItem = storageRepository.save(DtoToStorageConverter.covertToStorage(newStorageDto));
        if (savedItem != null) {
            result = false;
        }
        return result;
    }

    public void updateStorage(AddNewStorageDto newStorageDto) {
        Storage storage = storageRepository.findOne(newStorageDto.getId());
        storage.setAddress(newStorageDto.getAddress());
        storage.setCapacity(newStorageDto.getCapacity());
        storageRepository.save(storage);
    }

    public void deleteStorage(Long id) {
        storageRepository.delete(id);
    }
}
