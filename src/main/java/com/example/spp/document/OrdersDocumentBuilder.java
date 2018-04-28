package com.example.spp.document;

import com.example.spp.document.csv.CsvBuilder;
import com.example.spp.document.excel.ExcelBuilder;
import com.example.spp.document.pdf.PdfBuilder;
import com.example.spp.models.Item;
import com.example.spp.models.Order;
import com.example.spp.models.Storage;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.repository.ItemRepository;
import com.example.spp.repository.OrderRepository;
import com.example.spp.repository.StorageRepository;
import com.example.spp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("order_documents")
public class OrdersDocumentBuilder extends AbstractDocumentBuilder {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final StorageRepository storageRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public OrdersDocumentBuilder(UserRepository userRepository, ItemRepository itemRepository,
                                 OrderRepository orderRepository, StorageRepository storageRepository,
                                  ExcelBuilder excelBuilder, CsvBuilder csvBuilder, PdfBuilder pdfBuilder) {
        super(excelBuilder, csvBuilder, pdfBuilder);
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.storageRepository = storageRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    protected String getDocumentName() {
        return "List of orders";
    }

    @Override
    protected String[] getDocumentHeader() {
        return new String[] { "Amount", "Price", "Item", "Storage Adress", "Date", "Was there?"};
    }

    @Override
    protected int getExcelColumnWidth() {
        return 13;
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

        for (User oneItem : users) {
            List<Order> orders = orderRepository.findByUserId(oneItem.getId());
            for (Order order : orders) {
                List<String> item = new ArrayList<>();
                item.add(order.getAmount() + "");
                item.add(order.getTotalPrice() + "$");

                Item theItem = itemRepository.findOne(order.getItem().getId());
                item.add(theItem.getName());

                Storage storage = storageRepository.findOne(order.getStorage().getId());
                item.add(storage.getAddress());

                item.add(order.getDate());
                String isDone = order.isWasThere() ? "true" : "false";
                item.add(isDone);
                data.add(item);
            }
        }

        return data;
    }
}