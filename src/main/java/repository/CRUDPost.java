package repository;

import model.Company;
import model.Post;
import org.springframework.stereotype.Repository;
import setting.DataBaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CRUDPost {

    private static final Connection connection = DataBaseConnection.getConnection();

    public static void insertPost(Post toInsert) throws SQLException {

            String sql = "INSERT INTO Post (DatePost, TitlePost, DescriptionPost, ResponsibilityPost, requirement,Salary)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
                    statement.setDate(1, toInsert.getDatePost());
                    statement.setString(2, toInsert.getTitlePost());
                    statement.setString(3, toInsert.getDescriptionPost());
                    statement.setString(4, toInsert.getResponsibility());
                    statement.setString(5, toInsert.getRequirement());
                    statement.setDouble(6, toInsert.getSalary());
                    statement.executeUpdate();
                    System.out.println("Query successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<Post> findAllPost() {
        List<Post> postList = new ArrayList<>();

        String sql = "SELECT p.*, c.CompanyName, c.Address " +
                "FROM Post p " +
                "JOIN Company c ON p.IdCompany = c.IdCompany";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Company company = new Company(
                        resultSet.getInt("IdCompany"),
                        resultSet.getString("CompanyName"),
                        resultSet.getString("Address")
                );

                Post post = new Post(
                        resultSet.getInt("IdPost"),
                        resultSet.getDate("DatePost"),
                        resultSet.getString("TitlePost"),
                        resultSet.getString("DescriptionPost"),
                        resultSet.getString("responsibility"),
                        resultSet.getString("requirement"),
                        resultSet.getDouble("Salary"),
                        company
                );
                post.setCompany(company);

                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }


    public static void updatePost(Post toUpdate, int IdPost) throws SQLException {
        String sql = "UPDATE Post SET DatePost = ?, TitlePost = ?, DescriptionPost = ?, ResponsibilityPost = ?, Requirement = ?, Salary = ? WHERE IdPost = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, toUpdate.getDatePost());
            statement.setString(2, toUpdate.getTitlePost());
            statement.setString(3, toUpdate.getDescriptionPost());
            statement.setString(4, toUpdate.getResponsibility());
            statement.setString(5, toUpdate.getRequirement());
            statement.setDouble(6, toUpdate.getSalary());
            statement.setInt(7, IdPost);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deletePost(int postId) throws SQLException {
        String sql = "DELETE FROM Post WHERE IdPost = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, postId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

}
