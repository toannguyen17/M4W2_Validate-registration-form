package redt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import redt.model.FormRegister;
import redt.validation.FormValidation;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class FormController {

    @GetMapping
    public String doGet(Model model){
        model.addAttribute("register", new FormRegister());
        return "index";
    }

    @PostMapping
    public String doPost(@Valid @ModelAttribute("register") FormRegister register, BindingResult result){
        new FormValidation().validate(register, result);
        if (result.hasErrors()){
            result.getAllErrors().forEach(err -> {
                ObjectError objectError = (ObjectError) err;
                System.out.println(objectError.toString());
            });
            return "index";
        }
        return "result";
    }
}
