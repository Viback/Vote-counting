/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.benchpress;

/**
 *
 * @author Vikke
 */
public class Person {
    private String gender;
    private String bench;
    
    public Person(String gender, String bench)
    {
        this.gender = gender;
        this.bench = bench;
    }
    
    public String getGender()
    {
        return gender;
    }
    
    public String getBench()
    {
        return bench;
    }
    
}
