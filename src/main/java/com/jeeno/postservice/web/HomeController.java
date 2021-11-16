package com.jeeno.postservice.web;

import com.jeeno.postservice.web.dto.SessionUser;
import com.jeeno.postservice.web.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model){

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if( user != null ){
            model.addAttribute("userName", user.getName());
        }
        return "home";
    }
}
