package com.jeeno.postservice.web.controller;

import com.jeeno.postservice.web.dto.PostRequestDto;
import com.jeeno.postservice.web.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostPageController {

    private final PostsService postsService;

    @GetMapping("/post/save")
    public String saveForm(Model model){
        model.addAttribute("postForm", new PostRequestDto());
        return "post/createPostForm";
    }

    @PostMapping("/post/save")
    public String save(@Valid PostRequestDto dto, BindingResult result){
        if(result.hasErrors()){
            return "post/createPostForm";
        }

        postsService.save(dto);
        return "redirect:/";
    }
}
