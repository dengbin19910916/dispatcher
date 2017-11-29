package com.topscore.order.dispatcher.excel;

import com.topscore.order.dispatcher.domain.OrderMessageRepository;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EnableCaching
@Controller
@RequestMapping("/control-panel")
public class ExportController {

    @Autowired
    private OrderMessageRepository repository;

    @RequestMapping("/export")
    public String export(Model model) throws InterruptedException {
        List<Student> data = new ArrayList<>();
        for (int i = 0; i++ < 2_000; ) {
            data.add(new Student("邓斌", RandomUtils.nextInt(16, 40), LocalDateTime.now(),
                    RandomUtils.nextInt(50, 100), new BigDecimal(RandomUtils.nextInt(3000, 500000))));
        }
        model.addAttribute("data", data);
//
////        List<OrderMessage> data = repository.findAll();
////        model.addAttribute("data", data);
//
//        DataPump dataPump = new DataPump<OrderMessage>() {
//            private int index;
//            @Override
//            public List<OrderMessage> getData() {
//                return repository.findAll();
//            }
//
//            @Override
//            public boolean hasNext() {
//                return index++ < 10;
//            }
//        };
//        model.addAttribute(ExcelView.DATA_KEY, data);
//        model.addAttribute(ExcelView.TYPE, OrderMessage.class);

        return "excelView";
    }
}
