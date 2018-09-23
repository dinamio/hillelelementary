package com.entity;

public class Product {
    private Integer id;
    private  String name;
    private Producer producer;
    private Integer producer_id;

    public Product() {
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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Integer getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(Integer producer_id) {
        this.producer_id = producer_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer=" + producer +
                '}';
    }
}
