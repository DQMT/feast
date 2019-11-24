package xyz.tincat.host.feast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {
    @RequestMapping({"","404"})
    public String index() {
        return "404";
    }

    @RequestMapping("blank")
    public String blank() {
        return "blank";
    }

    @RequestMapping("buttons")
    public String buttons() {
        return "buttons";
    }


    @RequestMapping("cards")
    public String cards() {
        return "cards";
    }

    @RequestMapping("charts")
    public String charts() {
        return "charts";
    }

    @RequestMapping("forgot-password")
    public String forgot() {
        return "forgot-password";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("tables")
    public String tables() {
        return "tables";
    }

    @RequestMapping("utilities-animation")
    public String utilitiesAnimation() {
        return "utilities-animation";
    }

    @RequestMapping("utilities-border")
    public String utilitiesBorder() {
        return "utilities-border";
    }

    @RequestMapping("utilities-color")
    public String utilitiesColor() {
        return "utilities-color";
    }

    @RequestMapping("utilities-other")
    public String utilitiesOther() {
        return "utilities-other";
    }
}
