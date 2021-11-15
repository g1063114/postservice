package com.jeeno.postservice.web.controller;

import com.jeeno.postservice.web.dto.PostRequestDto;
import com.jeeno.postservice.web.dto.PostResponseDto;
import com.jeeno.postservice.web.dto.PostUpdateDto;
import com.jeeno.postservice.web.service.PostsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/post")
    public String postList(Model model){
        List<PostResponseDto> list = postsService.findAllDesc();
        model.addAttribute("list", list);
        return "post/postList";
    }

    @GetMapping("/post/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model){
        PostResponseDto dto = postsService.findById(id);
        model.addAttribute("form", dto);
        return "post/editForm";
    }

    @PostMapping("/post/{id}/edit")
    public String update(@PathVariable("id") Long id, @ModelAttribute("form")PostUpdateDto updateDto){
        PostUpdateDto dto = PostUpdateDto.builder()
                .title(updateDto.getTitle())
                .content(updateDto.getContent())
                .build();
        postsService.update(id, updateDto);
        return "redirect:/post";
    }

    @PostMapping("/post/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        postsService.delete(id);
        return "redirect:/post";
    }
}
