package com.jeeno.postservice.web.repository;

import com.jeeno.postservice.web.domain.Posts;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void clean(){
        postsRepository.deleteAll();
    }

    @Test
    public void post_저장_불러오기(){
        // given
        String title = "테스트";
        String content = "테스트";
        String author = "지노";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build()
        );

        // when
        List<Posts> all = postsRepository.findAll();

        // then
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
    }
}