package com.example.spp.document;

import com.example.spp.document.csv.CsvBuilder;
import com.example.spp.document.excel.ExcelBuilder;
import com.example.spp.document.pdf.PdfBuilder;
import com.example.spp.models.*;
import com.example.spp.models.enums.UserRole;
import com.example.spp.repository.ItemRepository;
import com.example.spp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("taxes_documents")
public class TaxesDocumentBuilder extends AbstractDocumentBuilder {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public TaxesDocumentBuilder(UserRepository userRepository, ItemRepository itemRepository,
                                   ExcelBuilder excelBuilder, CsvBuilder csvBuilder, PdfBuilder pdfBuilder) {
        super(excelBuilder, csvBuilder, pdfBuilder);
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    protected String getDocumentName() {
        return "Taxes for " + new Date();
    }

    @Override
    protected String[] getDocumentHeader() {
        return new String[] {"#", "Source", "Amount"};
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

        List<User> users;

        users = userRepository.findByRole(UserRole.ROLE_DRIVER);
        long sum = 0;
        for (User oneItem : users) {
            sum += oneItem.getSalary();
        }
        List<String> item1 = new ArrayList<>();
        item1.add("1");
        item1.add("Drivers");
        item1.add(sum + "$");
        data.add(item1);

        users = userRepository.findByRole(UserRole.ROLE_ACCOUNTER);
        sum = 0;
        for (User oneItem : users) {
            sum += oneItem.getSalary();
        }
        List<String> item2 = new ArrayList<>();
        item2.add("2");
        item2.add("Accounters");
        item2.add(sum + "$");
        data.add(item2);

        List<Item> items;

        items = itemRepository.findAll();
        int num = 3;
        for (Item oneItem : items) {
            List<String> item = new ArrayList<>();
            item.add(num++ + "");
            item.add(oneItem.getName());
            item.add(String.format("%s$", oneItem.getPrice()));
            data.add(item);
        }
        return data;
    }
}
