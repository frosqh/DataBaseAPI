package com.frosqh.dataBaseAPI;

public class Field {
    private String name;
    private String type;

    public Field(String name, String type){
        this.name = name;
        this.type = type;
    }

    public Field(String name){
        this.name = name;
        type = SQLTypes.INTEGER;
    }

    public String getName(){
        return name;
    }
}
