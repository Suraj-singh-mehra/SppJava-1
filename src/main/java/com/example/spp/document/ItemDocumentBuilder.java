package com.example.spp.document;

import com.example.spp.document.csv.CsvBuilder;
import com.example.spp.document.excel.ExcelBuilder;
import com.example.spp.document.pdf.PdfBuilder;
import com.example.spp.models.Item;
import com.example.spp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("item_documents")
public class ItemDocumentBuilder extends AbstractDocumentBuilder {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemDocumentBuilder(ItemRepository itemRepository,
                               ExcelBuilder excelBuilder, CsvBuilder csvBuilder, PdfBuilder pdfBuilder) {
        super(excelBuilder, csvBuilder, pdfBuilder);
        this.itemRepository = itemRepository;
    }

    @Override
    protected String getDocumentName() {
        return "Items";
    }

    @Override
    protected String[] getDocumentHeader() {
        return new String[] { "Category", "Name", "Price"};
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

        List<Item> items;

        items = itemRepository.findAll();

        for (Item oneItem : items) {
            List<String> item = new ArrayList<>();
            item.add(oneItem.getCategory());
            item.add(oneItem.getName());
            item.add(String.format("%s$", oneItem.getPrice()));
            data.add(item);
        }

        return data;
    }
}
