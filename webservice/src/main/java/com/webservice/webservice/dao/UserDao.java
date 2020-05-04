package com.webservice.webservice.dao;

import com.webservice.webservice.beans.User;
import com.webservice.webservice.exception.BadRequestException;
import com.webservice.webservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UserDao {
    private final UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user) {
        User userResponse;
        if (user.getId() == null && user.getName()!=null) {
            userResponse = userRepository.save(user);
        }else{
            throw new BadRequestException("Json format");
        }
        return userResponse;
    }

    public User findOne(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean deleteById(int id) {
        User userFind = findOne(id);
        boolean result = false;
        if (userFind!=null){
            userRepository.delete(userFind);
            result=true;
        }
        return result;
    }

}
