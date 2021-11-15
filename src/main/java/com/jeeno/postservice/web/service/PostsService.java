package com.jeeno.postservice.web.service;

import com.jeeno.postservice.web.domain.Posts;
import com.jeeno.postservice.web.dto.PostRequestDto;
import com.jeeno.postservice.web.dto.PostResponseDto;
import com.jeeno.postservice.web.dto.PostUpdateDto;
import com.jeeno.postservice.web.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostRequestDto dto){
        Long id = postsRepository.save(dto.toEntity()).getId();
        return id;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllDesc(){
        return postsRepository.findByDesc().stream()
                .map(posts -> new PostResponseDto(posts))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 없습니다. id = " + id)
        );
        return new PostResponseDto(posts);
    }

    @Transactional
    public Long update(Long id, PostUpdateDto dto){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 없습니다. id = " + id)
        );
        posts.update(dto.getTitle(), dto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 글이 없습니다. id = " + id)
        );
        postsRepository.delete(posts);
    }
}
