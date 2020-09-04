package com.example.retrofitdemo.mvp.module;

public class Repo {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                '}';
    }
}
