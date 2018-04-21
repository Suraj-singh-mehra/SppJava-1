package com.example.spp.document;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.spp.service.DocumentBuilder;
import com.example.spp.document.csv.CsvBuilder;
import com.example.spp.document.excel.ExcelBuilder;
import com.example.spp.document.pdf.PdfBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class AbstractDocumentBuilder implements DocumentBuilder {
    protected final String ARGS_ERROR_MESSAGE = "Invalid arguments";

    private final String PDF_WATER_MARK = "Tsyulia Lisa, Maria Melnik, Shautsova Tamara";
    private final ExcelBuilder excelBuilder;
    private final CsvBuilder csvBuilder;
    private final PdfBuilder pdfBuilder;

    @Autowired
    public AbstractDocumentBuilder(ExcelBuilder excelBuilder, CsvBuilder csvBuilder, PdfBuilder pdfBuilder) {
        this.excelBuilder = excelBuilder;
        this.csvBuilder = csvBuilder;
        this.pdfBuilder = pdfBuilder;
    }

    protected abstract String getDocumentName();
    protected abstract String[] getDocumentHeader();
    protected abstract List<List<String>> getDocumentData(Object[] args) throws Exception;
    protected abstract int getExcelColumnWidth();
    protected abstract boolean isPdfPortrait();

    @Override
    public void generateExcelDocument(OutputStream outputStream, Object... args) throws Exception {
        excelBuilder.buildDocument(outputStream, getDocumentName(), getDocumentHeader(), getDocumentData(args),
                getExcelColumnWidth());
    }

    @Override
    public void generateCsvDocument(OutputStream outputStream, Object... args) throws Exception {
        csvBuilder.buildDocument(outputStream, getDocumentHeader(), getDocumentData(args));
    }

    @Override
    public void generatePdfDocument(OutputStream outputStream, Object... args) throws Exception {
        pdfBuilder.buildDocument(outputStream, getDocumentHeader(), getDocumentData(args), PDF_WATER_MARK, isPdfPortrait());
    }
}
