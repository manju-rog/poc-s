package com.training.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticatedPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    @GetMapping("/")
    public String home(@AuthenticationPrincipal Saml2AuthenticatedPrincipal principal, Model model) {
        if (principal == null) {
            System.out.println("No principal found");
            return "error";
        }
        
        model.addAttribute("name", principal.getName());
        model.addAttribute("emailAddress", principal.getFirstAttribute("email"));
        model.addAttribute("userAttributes", principal.getAttributes());
        
        logger.info("User successfully authenticated: {}", principal.getName());
        return "home";
    }
}