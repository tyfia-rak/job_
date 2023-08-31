package repository;

import model.Company;
import org.springframework.stereotype.Repository;
import setting.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CRUDCompany {
    private static final Connection connection = DataBaseConnection.getConnection();
    public static List<Company> findAllCompany() {
        List<Company> CompanyList = new ArrayList<>();

        String sql = "SELECT * FROM Company";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Company company = new Company(
                        resultSet.getInt("IdCompany"),
                        resultSet.getString("CompanyName"),
                        resultSet.getString("Address")
                );
                CompanyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CompanyList;
    }

    public static void insertCompany(Company toInsert) throws SQLException {

        String sql = "INSERT INTO Company (CompanyName, Address)"
                + " VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, toInsert.getCompanyName());
            statement.setString(2, toInsert.getAddress());
            statement.executeUpdate();
            System.out.println("Query successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCompany(Company toUpdate, int IdCompany) throws SQLException {
        String sql = "UPDATE Company SET CompanyName = ?, Address = ? WHERE IdCompany = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, toUpdate.getCompanyName());
            statement.setString(2, toUpdate.getAddress());
            statement.setInt(3, IdCompany);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteCompany(int IdCompany) throws SQLException {
        String sql = "DELETE FROM Company WHERE IdCompany = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, IdCompany);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
