package com.example.login.controller;

import com.example.login.model.User;
import com.example.login.model.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Optional;

@Controller
@RequestMapping("")
public class recoveryController {

    @Autowired
    private UserDAO userDAO;
    private JavaMailSender javaMailSender;

    @RequestMapping(value = "recovery", method = RequestMethod.GET)
    public String index(Model modelo) {
        modelo.addAttribute("form",true);
    return "recovery";
    }

    @RequestMapping(value = "/recovery", method = RequestMethod.POST)
    public String forgotUserPassword(Model model, @RequestParam(name = "email") String email) {
        User existingUser = userDAO.findByEmailIgnoreCase(email);

        if (existingUser != null) {
            // Create token
            String userInfo = existingUser.toString();


            model.addAttribute("message1", "Request to reset password received. Check your inbox for the reset link.");
            model.addAttribute("message2","To complete the password reset process, please click here: "
                    + "http://localhost:8082/confirm-reset?token=" + userInfo);
            model.addAttribute("existe",true);

        }else{
            model.addAttribute("nexiste",true);
        }
        return "recovery";

    }
}