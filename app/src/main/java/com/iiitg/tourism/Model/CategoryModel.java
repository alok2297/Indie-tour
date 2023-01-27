package com.iiitg.tourism.Model;

public class CategoryModel {
    String name,sub,desp1,desp2,pic,pic1,pic2,link,Flink,yt,grid;

    public CategoryModel(){}

    public CategoryModel(String name, String sub, String desp1, String desp2, String pic, String pic1, String pic2, String link,String Flink,String yt,String grid) {
        this.name = name;
        this.sub = sub;
        this.desp1 = desp1;
        this.desp2 = desp2;
        this.pic = pic;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.link = link;
        this.Flink = Flink;
        this.yt = yt;
        this.grid = grid;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getFlink() {
        return Flink;
    }

    public void setFlink(String Flink) {
        this.Flink = Flink;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDesp1() {
        return desp1;
    }

    public void setDesp1(String desp1) {
        this.desp1 = desp1;
    }

    public String getDesp2() {
        return desp2;
    }

    public void setDesp2(String desp2) {
        this.desp2 = desp2;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
