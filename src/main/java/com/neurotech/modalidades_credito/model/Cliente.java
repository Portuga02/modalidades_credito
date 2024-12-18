package com.neurotech.modalidades_credito.model;

public class Cliente {
    private String name;
    private int age;
    private double income;

    // Construtores, Getters e Setters
    public Cliente(String name, int age, double income) {
        this.name = name;
        this.age = age;
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
