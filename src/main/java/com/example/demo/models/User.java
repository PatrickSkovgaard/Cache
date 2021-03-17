package com.example.demo.models;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.TimeUnit;

public class User {

    private String id;


    public User (String id){
        this.id = id;
    }


    public String getDataSlow() throws InterruptedException{
        TimeUnit.SECONDS.sleep(10);
        String generatedString = RandomStringUtils.randomAlphabetic(1000);

        return generatedString;
    }


    public String getId(){
        return id;
    }
}