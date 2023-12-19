package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                 "INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)",
                 PreparedStatement.RETURN_GENERATED_KEYS)) {
             statement.setInt(1, dealershipId);
             statement.setString(2, vin);
             statement.executeUpdate();

             try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                 if (generatedKeys.next()) {
                     int dealership = generatedKeys.getInt(1);
                     String vehicleId = generatedKeys.getString(2);
                     System.out.println("Vehicle added to dealership!\n Vehicle Vin: " + vehicleId + "\n Dealership ID: " + dealership);
                 }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM inventory WHERE VIN = ?")) {
            statement.setString(1, vin);
            statement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

