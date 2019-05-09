package com.example.demo.domain;


public class Game {

    private int id;
    private String cnName;
    private String jpName;
    private String enName;
    private String nature;
    private String generation;
    private String power;
    private String hirate;
    private String type;
    private String pp;

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", cnName='" + cnName + '\'' +
                ", jpName='" + jpName + '\'' +
                ", enName='" + enName + '\'' +
                ", nature='" + nature + '\'' +
                ", generation='" + generation + '\'' +
                ", power='" + power + '\'' +
                ", hirate='" + hirate + '\'' +
                ", type='" + type + '\'' +
                ", pp='" + pp + '\'' +
                '}';
    }

    public Game() {
    }

    public Game(int id, String cnName, String jpName, String enName, String nature, String generation, String power, String hirate, String type, String pp) {
        this.id = id;
        this.cnName = cnName;
        this.jpName = jpName;
        this.enName = enName;
        this.nature = nature;
        this.generation = generation;
        this.power = power;
        this.hirate = hirate;
        this.type = type;
        this.pp = pp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getJpName() {
        return jpName;
    }

    public void setJpName(String jpName) {
        this.jpName = jpName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getHirate() {
        return hirate;
    }

    public void setHirate(String hirate) {
        this.hirate = hirate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }
}
