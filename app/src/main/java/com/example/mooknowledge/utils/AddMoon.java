package com.example.mooknowledge.utils;

public class AddMoon {
    public String name;
    public String size;
    public String distance;
    public String orbits;
    public String[] facts;
    public int img;
    public int fontSize;

    public AddMoon(String name, String size, String distance, String orbits, String[] facts, int img, int fontSize) {
        this.name = name;
        this.size = size;
        this.distance = distance;
        this.orbits = orbits;
        this.img = img;
        this.fontSize = fontSize;
        this.facts = facts;
    }
}
