package com.jeeno.postservice.web.service;

import com.jeeno.postservice.web.dto.PostRequestDto;
import com.jeeno.postservice.web.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostRequestDto dto){
        Long id = postsRepository.save(dto.toEntity()).getId();
        return id;
    }
}
