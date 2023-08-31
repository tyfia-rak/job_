package Service;

import model.Post;
import model.response;
import org.springframework.stereotype.Service;
import repository.CRUDPost;
import java.sql.SQLException;

@Service
public class PostService {

    public static Post insert(Post toInsert){
        try{
            CRUDPost.insertPost(toInsert);
            return toInsert;
        }catch(SQLException e){
            throw new RuntimeException("There was an error when inserting the Post.");
        }
    }

    public static response update(Post toUpdate, int FindById) {
        try {
            CRUDPost.updatePost(toUpdate, FindById);
            return new response("Data update successfully");
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when updating the Post.");
        }
    }

    public static response delete(int postId) {
        try {
            boolean deleted = CRUDPost.deletePost(postId);
            if (deleted) {
                return new response("Post deleted successfully.");
            } else {
                throw new RuntimeException("Post not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("An error occurred while deleting the Post.");
        }
    }
}
