package mvc.controller;

import mvc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/demoJsp")
    public String demo() {
        return "demo";
    }

    @GetMapping("/listUsers")
    public String listUsers(ModelMap map) {
        map.put("userList", userService.getAllUsers());
        return "user";
    }
}
