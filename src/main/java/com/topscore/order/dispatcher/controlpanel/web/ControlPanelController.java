package com.topscore.order.dispatcher.controlpanel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/controlpanel")
public class ControlPanelController {

    @GetMapping
    public String index() {
        return "controlpanel/index";
    }
}
