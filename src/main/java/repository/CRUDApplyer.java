package repository;

import model.Applyer;
import model.Company;
import model.Post;
import org.springframework.stereotype.Repository;
import setting.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class CRUDApplyer {
    private static final Connection connection = DataBaseConnection.getConnection();

    public static void insertApplyer(Applyer toInsert) throws SQLException {

        String sql = "INSERT INTO Applyer (name, age, email, phoneNumber, applying_date, IdPost)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, toInsert.getName());
            statement.setInt(2, toInsert.getAge());
            statement.setString(3, toInsert.getEmail());
            statement.setString(4, toInsert.getPhoneNumber());
            statement.setDate(5, (Date) toInsert.getApplying_date());
            statement.setInt(6, toInsert.getPost().getIdPost());
            statement.executeUpdate();
            System.out.println("Query successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Applyer> findAllApplyer() {
        List<Applyer> applyerList = new ArrayList<>();

        String sql = "SELECT a.*, p.IdPost AS p_IdPost, p.DatePost AS p_DatePost, p.TitlePost AS p_TitlePost, p.DescriptionPost AS p_DescriptionPost, p.ResponsibilityPost AS p_ResponsibilityPost, p.requirement AS p_requirement, p.Salary AS p_Salary " +
                "FROM Applyer a " +
                "JOIN Post p ON a.IdPost = p.IdPost";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Company company = new Company(
                        resultSet.getInt("IdCompany"),
                        resultSet.getString("CompanyName"),
                        resultSet.getString("Address")
                );

                Post post = new Post(
                        resultSet.getInt("p_IdPost"),
                        resultSet.getDate("p_DatePost"),
                        resultSet.getString("p_TitlePost"),
                        resultSet.getString("p_DescriptionPost"),
                        resultSet.getString("p_ResponsibilityPost"),
                        resultSet.getString("p_requirement"),
                        resultSet.getDouble("p_Salary"),
                        company
                );

                Applyer applyer = new Applyer(
                        resultSet.getInt("IdApplyer"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("email"),
                        resultSet.getString("phoneNumber"),
                        resultSet.getDate("applying_date"),
                        post
                );
                applyer.setPost(post);

                applyerList.add(applyer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applyerList;
    }



    public static void updateApplyer(Applyer toUpdate, int idApplyer) throws SQLException {
        String sql = "UPDATE Applyer SET name = ?, age = ?, email = ?, phoneNumber = ?, applying_date = ?, IdPost = ? WHERE IdApplyer = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, toUpdate.getName());
            statement.setInt(2, toUpdate.getAge());
            statement.setString(3, toUpdate.getEmail());
            statement.setString(4, toUpdate.getPhoneNumber());
            statement.setDate(5, (Date) toUpdate.getApplying_date());
            statement.setInt(6, toUpdate.getPost().getIdPost());
            statement.setInt(7, idApplyer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteApplyer(int applyerId) throws SQLException {
        String sql = "DELETE FROM Applyer WHERE IdApplyer = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, applyerId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }




}
