package com.topscore.order.dispatcher.controlpanel;

import com.topscore.order.dispatcher.domain.OrderMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/control-panel")
public class ControlPanelController {

    @Autowired
    private OrderMessageRepository repository;

    @GetMapping
    public String index() {
        return "controlPanel/index";
    }

    @GetMapping("/order-messages")
    @ResponseBody
    public String getMessages() {
//        repository.findByCreatedTimeBetween();

        return null;
    }
}
