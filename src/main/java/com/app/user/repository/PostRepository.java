package com.app.user.repository;

import com.app.user.pojos.Post;
import com.app.user.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
