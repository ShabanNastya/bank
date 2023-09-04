package org.example.services;

import org.example.db.DbConnection;
import org.example.entity.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CheckService {

    public void generationCheck(UUID uuid) throws SQLException {
        Transaction tr = formTransaction(uuid);
        LinkedHashMap<String, String> checkMap = new LinkedHashMap<String, String>();
        checkMap.put("Check Id:", String.valueOf(tr.getUuid()));
        checkMap.put(String.valueOf(tr.getCreatedAt()), String.valueOf(tr.getCreatedAt()));
        if (tr.getToAccount() == 0) {
            checkMap.put("Bank:", tr.getBankFrom());
            checkMap.put("Customer:", String.valueOf(tr.getToAccount()));
        } else {
            checkMap.put("Sender's bank:", tr.getBankTo());
            checkMap.put("Receiver's bank:", tr.getBankFrom());
            checkMap.put("Customer:", String.valueOf(tr.getToAccount()));
        }
        checkMap.put("Amount:", String.valueOf(tr.getAmount()));
        StringBuilder checkString = showCheck(checkMap);

        String filePath = (System.getProperty("customer.dir") + "/checks" + tr.getUuid());

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath))) {
            printWriter.println(checkString);
            System.out.println("Check was create in" + filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private Transaction formTransaction(UUID uuid) throws SQLException {
        String sql = "SELECT * FROM transactions \n" +
                "LEFT JOIN customers \n" +
                "ON customers.customer_id = transactions.customer_id \n" +
                "LEFT JOIN banks \n" +
                "ON banks.id = customers.banks_id WHERE uuid = ?";
        Connection conn = DbConnection.provideConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1, uuid);
        ResultSet rs = ps.executeQuery();
        Transaction tr = null;
        if (rs.isBeforeFirst()) {
            while (rs.next()) {
                tr = new Transaction(((UUID) rs.getObject(1)),
                        rs.getDate(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        }
        if (tr.getToAccount() != 0) {
            CustomerService customerService = new CustomerService();
            BankService bankService = new BankService();
            tr.setBankTo(bankService.getBankNameById((int) customerService.getCustomerById(tr.getToAccount()).getCustomerBalance()));
        }
        return tr;
    }

    private StringBuilder showCheck(LinkedHashMap checkmap) {
        Set set = checkmap.entrySet();
        Iterator iterator = set.iterator();
        StringBuilder checkString = new StringBuilder();

        checkString.append("---------------------------------- \n");
        checkString.append("|          Bank Check            | \n");

        while (iterator.hasNext()) {
            StringBuilder x1 = new StringBuilder("|                                | \n");
            Map.Entry item = (Map.Entry) iterator.next();
            String key = item.getKey().toString();
            String value = item.getValue().toString();

            x1.replace(1, key.length(), key);
            x1.replace((x1.length() - value.length()), (x1.length() - 2), value);
            checkString.append(x1);
        }
        checkString.append("|                                | \n");
        checkString.append("---------------------------------- \n");


        return checkString;
    }
}
