package com.example.spp.document;

import com.example.spp.document.csv.CsvBuilder;
import com.example.spp.document.excel.ExcelBuilder;
import com.example.spp.document.pdf.PdfBuilder;
import com.example.spp.models.Company;
import com.example.spp.models.Item;
import com.example.spp.repository.CompanyRepository;
import com.example.spp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("provider_documents")
public class ProviderDocumentBuilder extends AbstractDocumentBuilder {
    private final CompanyRepository companyRepository;

    @Autowired
    public ProviderDocumentBuilder(CompanyRepository companyRepository,
                                    ExcelBuilder excelBuilder, CsvBuilder csvBuilder, PdfBuilder pdfBuilder) {
        super(excelBuilder, csvBuilder, pdfBuilder);
        this.companyRepository = companyRepository;
    }

    @Override
    protected String getDocumentName() {
        return "Providers";
    }

    @Override
    protected String[] getDocumentHeader() {
        return new String[] { "Id", "Email", "Name"};
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

        List<Company> companies;

        companies = companyRepository.findAll();

        for (Company oneCompany : companies) {
            List<String> item = new ArrayList<>();
            item.add(String.format("%s", oneCompany.getId()));
            item.add(oneCompany.getEmail());
            item.add(oneCompany.getName());
            data.add(item);
        }

        return data;
    }
}
