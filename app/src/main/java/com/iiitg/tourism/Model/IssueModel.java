package com.iiitg.tourism.Model;

public class IssueModel {
    String name,pic,email,sub;

    public IssueModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public IssueModel(String name, String pic, String email, String sub) {
        this.name = name;
        this.pic = pic;
        this.email = email;
        this.sub = sub;
    }
}
