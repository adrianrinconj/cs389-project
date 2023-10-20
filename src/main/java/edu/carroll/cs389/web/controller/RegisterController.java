package edu.carroll.cs389.web.controller;


import edu.carroll.cs389.jpa.model.User;
import edu.carroll.cs389.service.UserServiceImpl;
import edu.carroll.cs389.web.form.RegisterLoginForm;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




// this module is not implemented yet

@Controller
public class RegisterController {
    private static final Logger logInfo = LoggerFactory.getLogger((RegisterController.class));

    private final UserServiceImpl userServiceImpl;

    public RegisterController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }


    @GetMapping("/Register")
    public String RegisterGet(Model model) {
        model.addAttribute("registerLoginForm", new RegisterLoginForm());
        return "Register";
    }

    @PostMapping("/Register")
    public String RegisterPost(@Valid @ModelAttribute RegisterLoginForm registerLoginForm, BindingResult result, RedirectAttributes attrs) {

        if (!result.hasErrors()) {
            //logging
            logInfo.info("result.hasErrors() == false");

            boolean success = userServiceImpl.addUser(new User(registerLoginForm.getUsername(), registerLoginForm.getRawPassword()));
            if (success) {
                // something to add success popup or welcome
                return "WouldYouRatherEntry";
            }
            else {
                // something to add pop up or "username exists message"
                //logging debug
                logInfo.debug("result.hasErrors() == true");

            }
        }

        return "Register";
    }
}