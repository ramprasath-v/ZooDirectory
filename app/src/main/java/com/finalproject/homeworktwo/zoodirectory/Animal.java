package com.finalproject.homeworktwo.zoodirectory;

public class Animal {

    private String name;
    private String fileName;
    private String description;

    public Animal(String name, String fileName, String description) {
        this.name = name;
        this.fileName = fileName;
        this.description = description;
    }

    public String getName() {
        return name;
    } 

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }
}
