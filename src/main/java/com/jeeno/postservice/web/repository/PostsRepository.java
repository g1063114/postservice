package com.jeeno.postservice.web.repository;

import com.jeeno.postservice.web.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.createDate DESC")
    List<Posts> findByDesc();
}
