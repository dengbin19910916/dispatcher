package com.topscore.order.dispatcher.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * 订单报表仓库。
 *
 * @author dengb
 */
public interface OrderMessageRepository extends JpaRepository<OrderMessage, Long> {

    /**
     * 返回被创建的时间范围的订单报文列表。
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 订单报文列表
     */
    OrderMessage findByCreatedTimeBetween(LocalDateTime start, LocalDateTime end);

    /**
     * 返回被发送的时间范围的订单报文列表。
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 订单报文列表
     */
    OrderMessage findBySentTimeBetween(LocalDateTime start, LocalDateTime end);
}
