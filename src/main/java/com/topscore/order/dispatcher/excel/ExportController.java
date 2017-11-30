package com.topscore.order.dispatcher.excel;

import com.topscore.order.dispatcher.domain.OrderMessageRepository;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableCaching
@Controller
@RequestMapping("/control-panel")
public class ExportController {

    @Autowired
    private OrderMessageRepository repository;

    @RequestMapping("/export")
    public String export(Model model) throws InterruptedException {
//        List<Student> data = new ArrayList<>();
//        for (int i = 0; i++ < 500_000; ) {
//            data.add(new Student("邓斌", RandomUtils.nextInt(16, 40), LocalDateTime.now(),
//                    RandomUtils.nextInt(50, 100), new BigDecimal(RandomUtils.nextInt(3000, 500000))));
//        }

        DataPump<Student> data = new DataPump<Student>() {
            private int  count = 1;

            @Override
            public List<Student> getData() {
                List<Student> data = new ArrayList<>();
                for (int i = 0; i++ < 10; ) {
                    data.add(new Student("邓斌", RandomUtils.nextInt(16, 40), new Date(),
                            RandomUtils.nextInt(50, 100), new BigDecimal(RandomUtils.nextInt(3000, 500000))));
                }
                return data;
            }

            @Override
            public boolean hasNext() {
                return count++ <= 1;
            }
        };

        model.addAttribute("data", data);
//
////        List<OrderMessage> data = repository.findAll();
////        model.addAttribute("data", data);
//
//        DataPump data = new DataPump<OrderMessage>() {
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

//        return "excelView";
        return "csvView";
    }

    //    @RequestMapping("/export")
    public void exportFile(Model model, HttpServletResponse response) throws IOException, InterruptedException {
        response.setCharacterEncoding("gbk");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=" +
                new String("导出.csv".getBytes(), StandardCharsets.ISO_8859_1));
        PrintWriter writer = response.getWriter();

        List<Object> headers = new ArrayList<>();
        headers.add("编号");
        headers.add("名称");

        // head
        writeRow(headers, writer);
        writer.flush();

        // body
        for (int i = 1; i < 10; i++) {
            List<Object> row = new ArrayList<>();
            row.add(i);
            row.add("KC" + i);
            writeRow(row, writer);
            writer.flush();
        }
    }

    private static void writeRow(List<Object> row, PrintWriter csvWriter) {
        for (Object data : row) {
            String rowStr = "\"" + data + "\",";
            csvWriter.print(rowStr);
        }
        csvWriter.println();
        csvWriter.flush();
//        csvWriter.newLine();
    }
}
