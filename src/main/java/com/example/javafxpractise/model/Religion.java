package com.example.javafxpractise.model;

public class Religion {
    int id;
    String name;

    public Religion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Religion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
