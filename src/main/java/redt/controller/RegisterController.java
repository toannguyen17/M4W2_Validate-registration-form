package redt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redt.form.FormRegister;
import redt.model.Users;
import redt.service.UserService;
import redt.validation.FormValidation;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String doGet(Model model){
        model.addAttribute("register", new FormRegister());
        return "register";
    }

    @PostMapping
    public String doPost(@Valid @ModelAttribute("register") FormRegister register, BindingResult result, Model model){
        new FormValidation().validate(register, result);
        if (result.hasErrors()){
            return "register";
        }
        Users user = new Users();
        user.setName(register.getName());
        user.setEmail(register.getEmail());
        user.setPassword(register.getPassword());
        userService.save(user);
        model.addAttribute("users", user);
        return "result";
    }
}
