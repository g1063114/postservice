package com.jeeno.postservice.web.service;

import com.jeeno.postservice.web.dto.PostRequestDto;
import com.jeeno.postservice.web.dto.PostResponseDto;
import com.jeeno.postservice.web.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
