package com.example.spp.controllers;

import com.example.spp.models.enums.DocumentFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    @RequestMapping(value = "/printListOfUsers/{format}", method = RequestMethod.GET)
    public void getCompanies(@PathVariable(name = "format") DocumentFormat format) {

    }
}
