package com.example.spp.converter;

import com.example.spp.dto.AddNewItemDto;
import com.example.spp.models.Item;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DtoToItemConverter {
    public static Item covertToItem(AddNewItemDto newItemDto) {
        Item result = new Item();
        result.setCategory(newItemDto.getCategory());
        result.setName(newItemDto.getName());
        result.setPrice(newItemDto.getPrice());
        String dateString = newItemDto.getExpiryDate();
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        try {
            Date date = new java.sql.Date(format.parse(dateString).getTime());
            result.setExpireDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
