package com.topscore.order.dispatcher.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderMessage {
    /**
     * 订单主键。
     */
    @EmbeddedId
    private Id id;
    /**
     * 原始报文。
     */
    private String content;
    /**
     * 报文创建的时间。
     */
    private LocalDateTime createdTime;

    /**
     * 报文消息发给MQ的时间。
     */
    private LocalDateTime sentTime;

    /**
     * 报文消息类型。
     */
    public enum Type {
        VIP,
        ALI,
        JD
    }

    @Data
    @Embeddable
    public static class Id implements Serializable {
        private static final long serialVersionUID = 928232073465428072L;
        /**
         * 订单号。
         */
        @Column(length = 30)
        private String id;
        /**
         * 报文类型。
         */
        @Enumerated
        private Type type;
    }
}
