package com.example.spp.converter;

import com.example.spp.dto.AddNewStorageDto;
import com.example.spp.models.Storage;

public class DtoToStorageConverter {
    public static Storage covertToStorage(AddNewStorageDto newStorageDto) {
        Storage result = new Storage();
        result.setAddress(newStorageDto.getAddress());
        result.setCapacity(newStorageDto.getCapacity());
        return result;
    }
}
