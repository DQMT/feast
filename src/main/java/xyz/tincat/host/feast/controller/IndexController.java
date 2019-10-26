package xyz.tincat.host.feast.controller;

import org.springframework.stereotype.Controller;
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
}