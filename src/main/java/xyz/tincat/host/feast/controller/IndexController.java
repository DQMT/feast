package xyz.tincat.host.feast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Date       ：Created in 15:14 2019/10/26
 * @ Modified By：
 * @ Version:     0.1
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping({"","index"})
    public String index() {
        return "index";
    }

    @GetMapping("udpClient")
    public String udpClient() {
        return "udp-client";
    }

    @GetMapping("buttons")
    public String buttons() {
        return "buttons";
    }
}