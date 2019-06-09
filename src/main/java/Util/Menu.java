package Util;


import java.sql.Date;

public class Menu {
    private String url;
    private String name;
    private String material;
    private String steps;
    private Date date;
    private float score;
    private String PicPath;
    private String kouwei;

    public  Menu(){
        this.score=0;
        this.date=new Date(new java.util.Date().getTime());
        this.url="";
        this.PicPath="D:\\Workplace\\lucenedemo\\src\\main\\resources\\Picture\\null.jpg";
    }

    public float getScore(){
        return score;
    }
    public void setScore(float score){
        this.score=score;
    }

    public String getPicPath() {
        return PicPath;
    }

    public void setPicPath(String picPath) {
        this.PicPath = picPath;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public String getSteps() {
        return steps;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getKouwei() {
        return kouwei;
    }

    public void setKouwei(String kouwei) {
        this.kouwei = kouwei;
    }

    @Override
    public String toString(){
        String content="name:"+name+"\nurl:"+url+"\nmaterial"+material+"\nsteps:"+steps+"\nPath:"+PicPath;
        return content;
    }


}