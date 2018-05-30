package com.lxc.oauth.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/test")
    @ResponseBody
    public String getOrder() {
        return "ttest public ";
    }


}
