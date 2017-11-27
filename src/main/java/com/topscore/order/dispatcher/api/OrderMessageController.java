package com.topscore.order.dispatcher.api;

import com.topscore.order.dispatcher.domain.OrderMessage;
import com.topscore.order.dispatcher.domain.OrderMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderMessageController {

    @Autowired
    private OrderMessageRepository repository;

    @GetMapping("/order-messages/ids")
    @ResponseBody
    public List<String> ids(@PathParam("keyword") String keyword) {
        System.out.println("keyword: " + keyword);
        return Arrays.asList(data);
    }

    @GetMapping("/order-messages")
    public List<OrderMessage> orderMessages() {
        return repository.findAll();
    }

    private static String[] data = new String[]{
            "Albania",
            "Andorra",
            "Armenia",
            "Austria",
            "Azerbaijan",
            "Belarus",
            "Belgium",
            "Bosnia Herzegovina",
            "Bulgaria",
            "Croatia",
            "Cyprus",
            "Czech Republic",
            "Denmark",
            "Estonia",
            "Finland",
            "France",
            "Georgia",
            "Germany",
            "Greece",
            "Hungary",
            "Iceland",
            "Ireland",
            "Italy",
            "Kosovo",
            "Latvia",
            "Liechtenstein",
            "Lithuania",
            "Luxembourg",
            "Macedonia",
            "Malta",
            "Moldova",
            "Monaco",
            "Montenegro",
            "Netherlands",
            "Norway",
            "Poland",
            "Portugal",
            "Romania",
            "Russia",
            "San Marino",
            "Serbia",
            "Slovakia",
            "Slovenia",
            "Spain",
            "Sweden",
            "Switzerland",
            "Turkey",
            "Ukraine",
            "United Kingdom",
            "Vatican City"
    };
}
