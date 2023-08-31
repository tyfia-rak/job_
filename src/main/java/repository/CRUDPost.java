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
        String sqlInsertCompany = "INSERT INTO Company (CompanyName, Address) VALUES (?, ?)";
        String sqlInsertPost = "INSERT INTO Post (DatePost, TitlePost, DescriptionPost, Responsibility, requirement, Salary, idcompany)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        String[] returnId = { "idcompany" };
        try (PreparedStatement insertCompanyStmt = connection.prepareStatement(sqlInsertCompany, returnId);
             PreparedStatement insertPostStmt = connection.prepareStatement(sqlInsertPost, returnId)) {

            // Insert the company if it doesn't exist
            insertCompanyStmt.setString(1, toInsert.getCompany().getCompanyName());
            insertCompanyStmt.setString(2, toInsert.getCompany().getAddress());
            insertCompanyStmt.executeUpdate();

            // Retrieve the generated company ID
            int companyId;
            try (ResultSet generatedKeys = insertCompanyStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    companyId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating company failed, no ID obtained.");
                }
            }

            // Insert the post with the associated company ID
            insertPostStmt.setDate(1, toInsert.getDatePost());
            insertPostStmt.setString(2, toInsert.getTitlePost());
            insertPostStmt.setString(3, toInsert.getDescriptionPost());
            insertPostStmt.setString(4, toInsert.getResponsibility());
            insertPostStmt.setString(5, toInsert.getRequirement());
            insertPostStmt.setDouble(6, toInsert.getSalary());
            insertPostStmt.setInt(7, companyId);
            insertPostStmt.executeUpdate();

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
        String sql = "UPDATE Post SET DatePost = ?, TitlePost = ?, DescriptionPost = ?, Responsibility= ?, Requirement = ?, Salary = ? WHERE IdPost = ?";
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
