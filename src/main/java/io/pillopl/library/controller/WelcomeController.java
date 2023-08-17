package io.pillopl.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author o0wen0o
 * @create 2023-08-17 11:32 AM
 */
@Controller
public class WelcomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
