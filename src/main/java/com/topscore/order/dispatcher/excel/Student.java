package com.topscore.order.dispatcher.excel;

import com.topscore.order.dispatcher.excel.annotation.Document;
import com.topscore.order.dispatcher.excel.annotation.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(name = "花名册")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    public Student(String name, int age, LocalDateTime birthday, double grade, BigDecimal amount) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.grade = grade;
        this.amount = amount;

        this.name2 = name;
        this.age2 = age;
        this.birthday2 = birthday;
        this.grade2 = grade;
        this.amount2 = amount;

        this.name3 = name;
        this.age3 = age;
        this.birthday3 = birthday;
        this.grade3 = grade;
        this.amount3 = amount;

        this.name4 = name;
        this.age4 = age;
        this.birthday4 = birthday;
        this.grade4 = grade;
        this.amount4 = amount;

        this.name5 = name;
        this.age5 = age;
        this.birthday5 = birthday;
        this.grade5 = grade;
        this.amount5 = amount;

        this.name6 = name;
        this.age6 = age;
        this.birthday6 = birthday;
        this.grade6 = grade;
        this.amount6 = amount;

        this.name7 = name;
        this.age7 = age;
        this.birthday7 = birthday;
        this.grade7 = grade;
        this.amount7 = amount;

        this.name8 = name;
        this.age8 = age;
        this.birthday8 = birthday;
        this.grade8 = grade;
        this.amount8 = amount;

        this.name9 = name;
        this.age9 = age;
        this.birthday9 = birthday;
        this.grade9 = grade;
        this.amount9 = amount;

        this.name10 = name;
        this.age10 = age;
        this.birthday10 = birthday;
        this.grade10 = grade;
        this.amount10 = amount;
    }

    @Header(name = "身份证号码")
    private String idCard = "430821199111230033";

    @Header(name = "姓名")
    private String name;

    @Header(name = "年龄")
    private int age;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    @Header(name="成绩")
    private double grade;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount;

    @Header(name = "姓名")
    private String name2;

    @Header(name = "年龄")
    private int age2;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday2;

    @Header(name="成绩")
    private double grade2;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount2;

    @Header(name = "姓名")
    private String name3;

    @Header(name = "年龄")
    private int age3;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday3;

    @Header(name="成绩")
    private double grade3;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount3;

    @Header(name = "姓名")
    private String name4;

    @Header(name = "年龄")
    private int age4;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday4;

    @Header(name="成绩")
    private double grade4;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount4;

    @Header(name = "姓名")
    private String name5;

    @Header(name = "年龄")
    private int age5;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday5;

    @Header(name="成绩")
    private double grade5;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount5;

    @Header(name = "姓名")
    private String name6;

    @Header(name = "年龄")
    private int age6;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday6;

    @Header(name="成绩")
    private double grade6;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount6;

    @Header(name = "姓名")
    private String name7;

    @Header(name = "年龄")
    private int age7;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday7;

    @Header(name="成绩")
    private double grade7;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount7;

    @Header(name = "姓名")
    private String name8;

    @Header(name = "年龄")
    private int age8;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday8;

    @Header(name="成绩")
    private double grade8;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount8;

    @Header(name = "姓名")
    private String name9;

    @Header(name = "年龄")
    private int age9;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday9;

    @Header(name="成绩")
    private double grade9;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount9;

    @Header(name = "姓名")
    private String name10;

    @Header(name = "年龄")
    private int age10;

    @Header(name = "出生日期", width = 15)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday10;

    @Header(name="成绩")
    private double grade10;

    @Header(name = "金额")
    @NumberFormat(pattern = "#,###,00")
    private BigDecimal amount10;

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
