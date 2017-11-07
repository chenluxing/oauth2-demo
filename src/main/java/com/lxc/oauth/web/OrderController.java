package com.lxc.oauth.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @GetMapping("/{orderId}")
    @ResponseBody
    public String getOrder(@PathVariable("orderId") String orderId) {
        return "this is a order:" + orderId;
    }

}
