package com.example.spp.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface DocumentBuilder {
    void generateExcelDocument(OutputStream outputStream, Object... args) throws Exception;
    void generateCsvDocument(OutputStream outputStream, Object... args) throws Exception;
    void generatePdfDocument(OutputStream outputStream, Object... args) throws Exception;
}
