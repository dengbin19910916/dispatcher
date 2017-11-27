package com.topscore.order.dispatcher;

import com.google.gson.Gson;
import com.topscore.order.dispatcher.Sender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class TmallSender {



    public static void main(String[] args) {
        Gson gson = new Gson();
        String startCreated = "2017-04-04 18:10:05";
        String endCreated = "2017-04-04 18:15:08";
        int pageNo = 1;
        Map<String, String> param = new HashMap<String, String>();
        String shopCodes = "D003,D004,D017,D018,D062,D002,D066,D065,D001,D007,D006,D005,D071,D059";
        param.put("startCreated", startCreated);
        param.put("endCreated", endCreated);
        param.put("pageSize", "10");
        param.put("env", "prod");// 环境
        param.put("shopCodes", shopCodes);// 获取订单店铺值
        param.put("pageNo", String.valueOf(pageNo));
        RestTemplate restTemplate = new RestTemplate();
        Map result = restTemplate.postForObject("http://121.199.176.232:80" + "/channelServiceMvc/rds/tradesSoldGet", param, Map.class);
        System.err.println("tradesSoldGetRDS result: 【" + gson.toJson(result) + "】");
    }
}
