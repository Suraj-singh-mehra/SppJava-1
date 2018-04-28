package com.example.spp.document;

import com.example.spp.document.csv.CsvBuilder;
import com.example.spp.document.excel.ExcelBuilder;
import com.example.spp.document.pdf.PdfBuilder;
import com.example.spp.models.Company;
import com.example.spp.models.Storage;
import com.example.spp.repository.CompanyRepository;
import com.example.spp.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("storage_documents")
public class StorageDocumentBuilder extends AbstractDocumentBuilder {
    private final StorageRepository storageRepository;

    @Autowired
    public StorageDocumentBuilder(StorageRepository storageRepository,
                                   ExcelBuilder excelBuilder, CsvBuilder csvBuilder, PdfBuilder pdfBuilder) {
        super(excelBuilder, csvBuilder, pdfBuilder);
        this.storageRepository = storageRepository;
    }

    @Override
    protected String getDocumentName() {
        return "Storages";
    }

    @Override
    protected String[] getDocumentHeader() {
        return new String[] { "Id", "Address", "Capacity"};
    }

    @Override
    protected int getExcelColumnWidth() {
        return 23;
    }

    @Override
    protected boolean isPdfPortrait() {
        return true;
    }

    @Override
    protected List<List<String>> getDocumentData(Object[] args) {
        List<List<String>> data = new ArrayList<>();

        List<Storage> storages;

        storages = storageRepository.findAll();

        for (Storage storage : storages) {
            List<String> item = new ArrayList<>();
            item.add(storage.getId() + "");
            item.add(storage.getAddress());
            item.add(storage.getCapacity() + "");
            data.add(item);
        }

        return data;
    }
}
