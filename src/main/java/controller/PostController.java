package controller;

import Service.PostService;
import model.Post;
import model.response;
import org.springframework.web.bind.annotation.*;
import repository.CRUDPost;

import java.util.List;

@RestController
public class PostController {

    @GetMapping("/posts")
    public static List<Post> getAllPost(){
        return CRUDPost.findAllPost();
    }

    @PostMapping("/post")
    public static Post insertPost(@RequestBody Post toInsert){
        return PostService.insert(toInsert);
    }

    @PutMapping("/post/update/{FindById}")
    public static response updatePost(@RequestBody Post toUpdate, @PathVariable int FindById) {
        return PostService.update(toUpdate, FindById);
    }

    @DeleteMapping("/post/delete/{postId}")
    public static response deletePost(@PathVariable int postId) {
        return PostService.delete(postId);
    }

}
