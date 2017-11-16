package com.topscore.order.dispatcher;

import com.topscore.order.dispatcher.domain.OrderMessage;

import java.util.List;

/**
 * 抓取原始报文接口。
 */
public interface Fetcher {

    /**
     * 返回原始报文列表。
     *
     * @return 原始报文列表。
     */
    List<OrderMessage> fetch();
}
