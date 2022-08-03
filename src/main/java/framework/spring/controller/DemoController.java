package framework.spring.controller;

import framework.spring.pojo.User;
import framework.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/demoJsp")
    public String demo() {
        return "demo";
    }

    @ResponseBody
    @GetMapping("/listUsers")
    public List<User> listUsers() {
        return userService.getAllUsers();
    }
}
