package com.example.spp.controllers;

import com.example.spp.document.*;
import com.example.spp.models.enums.DocumentFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@SuppressWarnings("Duplicates")
@RequestMapping(value = "/document")
public class DocumentController {
    private final String ERROR_MESSAGE = "Unable to generate document";
    private final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";
    private final String CSV_CONTENT_TYPE = "text/csv";
    private final String PDF_CONTENT_TYPE = "application/pdf";

    private final EmployeeDocumentBuilder employeeDocumentBuilder;
    private final ItemDocumentBuilder itemDocumentBuilder;
    private final ProviderDocumentBuilder providerDocumentBuilder;
    private final DriversScheduleBuilder driversScheduleBuilder;
    private final TaxesDocumentBuilder taxesDocumentBuilder;
    private final StorageDocumentBuilder storageDocumentBuilder;

    @Autowired
    public DocumentController(@Qualifier("employee_documents") EmployeeDocumentBuilder employeeDocumentBuilder,
                              @Qualifier("item_documents") ItemDocumentBuilder itemDocumentBuilder,
                              @Qualifier("driver_schedule_documents") DriversScheduleBuilder driversScheduleBuilder,
                              @Qualifier("provider_documents") ProviderDocumentBuilder providerDocumentBuilder,
                              @Qualifier("taxes_documents") TaxesDocumentBuilder taxesDocumentBuilder,
                              @Qualifier("storage_documents") StorageDocumentBuilder storageDocumentBuilder) {
        this.employeeDocumentBuilder = employeeDocumentBuilder;
        this.itemDocumentBuilder = itemDocumentBuilder;
        this.providerDocumentBuilder = providerDocumentBuilder;
        this.driversScheduleBuilder = driversScheduleBuilder;
        this.taxesDocumentBuilder = taxesDocumentBuilder;
        this.storageDocumentBuilder = storageDocumentBuilder;
    }

    @GetMapping(value = "/printListOfUsers/XLSX")
    public void getEmployeeExcel(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(EXCEL_CONTENT_TYPE);
            employeeDocumentBuilder.generateExcelDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfUsers/CSV")
    public void getEmployeeCsv(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(CSV_CONTENT_TYPE);
            employeeDocumentBuilder.generateCsvDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfItems/PDF")
    public void getItemsPdf(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(PDF_CONTENT_TYPE);
            itemDocumentBuilder.generatePdfDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfItems/XLSX")
    public void getItemsExcel(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(EXCEL_CONTENT_TYPE);
            itemDocumentBuilder.generateExcelDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfItems/CSV")
    public void getItemsCsv(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(CSV_CONTENT_TYPE);
            itemDocumentBuilder.generateCsvDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfUsers/PDF")
    public void getEmployeePdf(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(PDF_CONTENT_TYPE);
            employeeDocumentBuilder.generatePdfDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfProviders/PDF")
    public void getProvidersPdf(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(PDF_CONTENT_TYPE);
            providerDocumentBuilder.generatePdfDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfProviders/XLSX")
    public void getProvidersExcel(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(EXCEL_CONTENT_TYPE);
            providerDocumentBuilder.generateExcelDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfProviders/CSV")
    public void getProvidersCsv(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(CSV_CONTENT_TYPE);
            providerDocumentBuilder.generateCsvDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printDriversSchedule/PDF")
    public void getDriversSchedulePdf(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(PDF_CONTENT_TYPE);
            driversScheduleBuilder.generatePdfDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printDriversSchedule/XLSX")
    public void getDriversScheduleExcel(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(EXCEL_CONTENT_TYPE);
            driversScheduleBuilder.generateExcelDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printDriversSchedule/CSV")
    public void getDriversScheduleCsv(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(CSV_CONTENT_TYPE);
            driversScheduleBuilder.generateCsvDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printTaxes/PDF")
    public void getTaxesPdf(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(PDF_CONTENT_TYPE);
            taxesDocumentBuilder.generatePdfDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printTaxes/XLSX")
    public void getTaxesExcel(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(EXCEL_CONTENT_TYPE);
            taxesDocumentBuilder.generateExcelDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printTaxes/CSV")
    public void getTaxesCsv(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(CSV_CONTENT_TYPE);
            taxesDocumentBuilder.generateCsvDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping(value = "/printListOfStorages/PDF")
    public void getStoragesPdf(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(PDF_CONTENT_TYPE);
            storageDocumentBuilder.generatePdfDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfStorages/XLSX")
    public void getStoragesExcel(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(EXCEL_CONTENT_TYPE);
            storageDocumentBuilder.generateExcelDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/printListOfStorages/CSV")
    public void getStoragesCsv(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.setContentType(CSV_CONTENT_TYPE);
            storageDocumentBuilder.generateCsvDocument(httpServletResponse.getOutputStream());
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
