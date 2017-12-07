package com.topscore.order.dispatcher.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.topscore.order.dispatcher.excel.annotation.Document;
import com.topscore.order.dispatcher.excel.annotation.Header;
import com.topscore.order.dispatcher.excel.annotation.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 订单报文。
 *
 * @author dengb
 */
@Document(name = "订单报文")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderMessage {
    /**
     * 订单号。
     */
    @Id
    @Column(length = 30)
    @Header(name = "订单号")
    private String id;
    /**
     * 报文类型。
     */
    @Enumerated
    @Header(name = "报文类型")
    @Dictionary(values = {
            @Dictionary.Map(value = "TMALL", text = "天猫"),
            @Dictionary.Map(value = "TAOBAO", text = "淘宝"),
            @Dictionary.Map(value = "JD", text = "京东"),
            @Dictionary.Map(value = "VIP", text = "唯品会")
    })
    private Type type;
    /**
     * 原始报文。
     */
    @Lob
    private String content;
    /**
     * 报文创建的时间。
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Header(name = "报文创建的时间")
    private LocalDateTime createdTime;

    /**
     * 报文消息发给MQ的时间。
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Header(name = "报文消息发给MQ的时间", width = 15)
    private LocalDateTime sentTime;

    @Header(name = "身份证号码")
    private String idCard = "430821199111230033";

    /**
     * 报文消息类型。
     */
    public enum Type {
        /**
         * 天猫。
         */
        TMALL,
        /**
         * 淘宝。
         */
        TAOBAO,
        /**
         * 京东。
         */
        JD,
        /**
         * 唯品会。
         */
        VIP
    }
}
