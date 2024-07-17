package org.example.dailyquiz2.Controller;

import org.example.dailyquiz2.Domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    private List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping("/index")
    public String test(Model model) {
        User max = new User(nextId++, "Kim", "1234@1234", true, "456789");
        model.addAttribute("user", max);
        return "index";
    }

    @GetMapping("/userList")
    public String list(Model model) {
        model.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/save")
    public String join(@ModelAttribute User user) {
        user.setId(nextId++);
        //user.setUsername(user.getUsername());
        //user.setEmail(user.getEmail());
        //user.setPassword(user.getPassword());
        user.setAdmin(false);
        users.add(user);
        System.out.println("유저 ID : " + user.getId());
        System.out.println("유저 이름 : " + user.getUsername());
        System.out.println("유저 이메일 : " + user.getEmail());
        System.out.println("유저 패스워드 : " + user.getPassword());
        System.out.println("어드민 여부 : " + user.isAdmin());
        return "redirect:/userList";
    }
}
