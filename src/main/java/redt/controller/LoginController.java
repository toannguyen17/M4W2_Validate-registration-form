package redt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redt.form.FormLogin;
import redt.form.FormRegister;
import redt.model.Users;
import redt.service.UserService;
import redt.validation.FormValidation;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String doGet(Model model){
        model.addAttribute("login", new FormLogin());
        return "login";
    }

    @PostMapping
    public String doPost(@Valid @ModelAttribute("login") FormLogin login, BindingResult result, Model model){
        if (result.hasErrors()){
            return "login";
        }

        Optional<Users> users = userService.findByEmail(login.getEmail());
        if (users.isPresent()){
            if (!users.get().getPassword().equals(login.getPassword())){
                result.rejectValue("login", "login.password.false");
            }
        }else{
            result.rejectValue("login", "login.email.false");
        }

        if (result.hasErrors()){
            return "login";
        }

        model.addAttribute("users", users.get());
        return "result";
    }
}
