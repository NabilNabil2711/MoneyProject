package org.example.money;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Helloworld {
    @GetMapping("testHtml")
    public String hello() {
        return "index";

    }
}
