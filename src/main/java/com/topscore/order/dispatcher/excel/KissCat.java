package com.topscore.order.dispatcher.excel;

public class KissCat {

    private Integer id;
    private String name;

    public KissCat() {
    }

    public KissCat(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
