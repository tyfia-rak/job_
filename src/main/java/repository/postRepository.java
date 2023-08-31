package repository;

import model.Post;

import java.sql.SQLException;
import java.util.List;

public interface postRepository{
    void insertPost(Post toInsert) throws SQLException;
    List<Post> findAllPost();
    void updatePost(Post toUpdate, int IdPost) throws SQLException;
    boolean deletePost(int postId) throws SQLException;
}
