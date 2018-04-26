package com.example.spp.controllers;

import com.example.spp.models.enums.DocumentFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {
    @RequestMapping(value = "/documents/printListOfUsers/{format}", method = RequestMethod.POST)
    public void getListOfUsers(@PathVariable(name = "format") DocumentFormat format) {
        System.out.println(format);
    }

    @RequestMapping(value = "/documents/printListOfItems/{format}", method = RequestMethod.POST)
    public void getListOfItems(@PathVariable(name = "format") DocumentFormat format) {

    }

    @RequestMapping(value = "/documents/printListOfProviders/{format}", method = RequestMethod.POST)
    public void getListOfProviders(@PathVariable(name = "format") DocumentFormat format) {

    }

    @RequestMapping(value = "/documents/printDriversSchedule/{format}", method = RequestMethod.POST)
    public void getDriversSchedule(@PathVariable(name = "format") DocumentFormat format) {

    }

    @RequestMapping(value = "/documents/printTaxes/{format}", method = RequestMethod.POST)
    public void getTaxes(@PathVariable(name = "format") DocumentFormat format) {

    }
}
