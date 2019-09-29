package com.example.demo.entity.test1;


import javax.persistence.*;

@Table(name = "User1")
public class User1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public User1() {
    }

    public User1(String name) {
        this.name = name;
    }

    public int getId() {
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
