package com.webservice.webservice.controller;

import com.webservice.webservice.beans.Post;
import com.webservice.webservice.beans.User;
import com.webservice.webservice.dao.PostDao;
import com.webservice.webservice.dao.UserDao;
import com.webservice.webservice.exception.ResourceNotFoundException;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDao userDao;
    private PostDao postDao;

    public UserController(UserDao userDao, PostDao postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    @GetMapping("/users/{id}/post")
    public List<Post> getAllPosts(@PathVariable int id){
        User user = userDao.findOne(id);
        if (user==null){
            throw new ResourceNotFoundException(String.format("id-%s",id));
        }
        return user.getPost();
    }

    @GetMapping("/users/{id}")
    public Resource<User> getUser(@PathVariable int id) {
        User userResponse = userDao.findOne(id);
        if (userResponse==null){
            throw new ResourceNotFoundException(String.format("id-%s",id));
        }
        //Return a resource where we can see all users
        Resource<User> resource=new Resource<>(userResponse);
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkBuilder.withRel("all-users"));
        return resource;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User userResponse = userDao.save(user);
        //See location resource created
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResponse.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/users/{id}/post")
    public ResponseEntity<Object> savePost(@PathVariable int id, @RequestBody Post post){
        User user = userDao.findOne(id);
        if (user==null){
            throw new ResourceNotFoundException(String.format("id-%s",id));
        }
        post.setUser(user);
        postDao.savePost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        boolean response = userDao.deleteById(id);
        if (!response){
            throw new ResourceNotFoundException(String.format("id-%s",id));
        }
    }
}
