package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        String query = "INSERT INTO sales_contract VALUES (?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, salesContract.getContractId());
            statement.setString(2, salesContract.getVin());
            statement.setObject(3, salesContract.getSaleDate());
            statement.setDouble(5, salesContract.getPrice());

            int row = statement.executeUpdate();
            System.out.println("Sales Contracted recorded! \n Row(s) updates " + row);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

