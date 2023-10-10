package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String allUsers(Model model) {
        List<User> users = userService.getUserList();
        model.addAttribute("allUser", users);
        return "all-user";
    }

    @RequestMapping("/addUsers")
    public String addNewUsers(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping("/upUser")
    public String upUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        System.out.println(user);
        return "redirect:/";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("userId") Long id, Model model) {
        User user = userService.getUser(id);
        user.setId(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
