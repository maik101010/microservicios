package com.webservice.webservice.dao;

import com.webservice.webservice.beans.Post;
import com.webservice.webservice.beans.User;
import com.webservice.webservice.exception.BadRequestException;
import com.webservice.webservice.repository.PostRepository;
import com.webservice.webservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDao {
    private final PostRepository postRepository;

    public PostDao(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post savePost(Post post) {
        Post postResponse;
        if (post.getDescription()!=null) {
            postResponse = postRepository.save(post);
        }else{
            throw new BadRequestException("Json format");
        }
        return postResponse;
    }

}
