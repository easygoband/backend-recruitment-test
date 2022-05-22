package com.easy.zssn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Survivor{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int age=0;
    private String gender;
    private boolean isInfected=false;
    private int reports=0;

    public void addReport(){
        reports++;
        if(reports>=3)
            isInfected=true;
    }

    public boolean isInfected() {
        return isInfected;
    }
    public void setInfected(boolean isInfected) {
        this.isInfected = isInfected;
    }
    public Integer getId() {
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    @Override
    public String toString(){
        return "id: "+this.id+"\n"+
        "name: "+this.name+"\n"+
        "age: "+this.age+"\n"+
        "gender: "+this.gender+"\n"+
        "isInfected: "+this.isInfected+"\n"+
        "reports: "+this.reports+"\n";
    }
}
