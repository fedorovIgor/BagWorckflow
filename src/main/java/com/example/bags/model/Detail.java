package com.example.bags.model;

import com.example.bags.model.Entity.DetailEntity;

public class Detail {

    private Integer id;
    private String name;
    private  int count;
    private int area;
    private int length;
    private int width;

    public Detail() {
    }

    public Detail(DetailEntity e) {
        this.name = e.getName();
        this.id = e.getId();
        this.count = e.getCount();
        this.area = e.getArea();
        this.length = e.getLength();
        this.width = e.getWidth();
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", area=" + area +
                ", length=" + length +
                ", width=" + width +
                '}';
    }
}
