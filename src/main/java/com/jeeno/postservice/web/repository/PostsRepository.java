package com.jeeno.postservice.web.repository;

import com.jeeno.postservice.web.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> {
}
