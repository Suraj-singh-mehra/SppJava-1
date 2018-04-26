package com.example.spp.document;

import com.example.spp.document.csv.CsvBuilder;
import com.example.spp.document.excel.ExcelBuilder;
import com.example.spp.document.pdf.PdfBuilder;
import com.example.spp.models.User;
import com.example.spp.models.enums.UserRole;
import com.example.spp.repository.UserRepository;
import org.dom4j.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("employee_documents")
public class EmployeeDocumentBuilder extends AbstractDocumentBuilder {

    private final UserRepository userRepository;

    @Autowired
    public EmployeeDocumentBuilder(UserRepository userRepository,
                                   ExcelBuilder excelBuilder, CsvBuilder csvBuilder, PdfBuilder pdfBuilder) {
        super(excelBuilder, csvBuilder, pdfBuilder);
        this.userRepository = userRepository;
    }

    @Override
    protected String getDocumentName() {
        return "Users";
    }

    @Override
    protected String[] getDocumentHeader() {
        return new String[] { "FullName", "E-mail", "Biography", "Role", "Salary" };
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

        users = userRepository.findAll();

        for (User user : users) {
            List<String> item = new ArrayList<>();
            item.add(user.getFullname());
            item.add(user.getEmail());
            item.add(user.getBiography());
            item.add(user.getRole().toString());
            item.add(String.format("%s$", user.getSalary()));
            data.add(item);
        }

        return data;
    }
}
