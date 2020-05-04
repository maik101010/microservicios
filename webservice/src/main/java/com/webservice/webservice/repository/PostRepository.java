package com.webservice.webservice.repository;

import com.webservice.webservice.beans.Post;
import com.webservice.webservice.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
