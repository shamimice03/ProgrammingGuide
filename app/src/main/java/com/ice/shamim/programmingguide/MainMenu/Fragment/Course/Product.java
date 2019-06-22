package com.ice.shamim.programmingguide.MainMenu.Fragment.Course;

public class Product {

    private String title;
    private String shortdesc;


    public Product() {
    }

    public Product(String title, String shortdesc) {
        this.title = title;
        this.shortdesc = shortdesc;
    }


    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }


}